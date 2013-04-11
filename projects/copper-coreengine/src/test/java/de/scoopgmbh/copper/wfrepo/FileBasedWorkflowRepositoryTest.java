/*
 * Copyright 2002-2013 SCOOP Software GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.scoopgmbh.copper.wfrepo;


import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import de.scoopgmbh.copper.WorkflowFactory;

public class FileBasedWorkflowRepositoryTest{
	
	@Test(expected = ClassNotFoundException.class)
	public void testCreateWorkflowFactory() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		FileBasedWorkflowRepository repo = new FileBasedWorkflowRepository();
		repo.addSourceDir("src/workflow/java");
		repo.setTargetDir("build/compiled_workflow");
		repo.start();
		try {
			WorkflowFactory<Object> factory = repo.createWorkflowFactory("foo");
			factory.newInstance();
		}
		finally {
			repo.shutdown();
		}
		
	}
	
	@Test
	public void testGetAll() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		FileBasedWorkflowRepository wfRepository = new FileBasedWorkflowRepository();
		wfRepository.setSourceDirs(Arrays.asList("src/test/java/de/scoopgmbh/copper/monitoring/integrationtest/workflow"));
		wfRepository.setTargetDir("build/compiled_workflow");
		wfRepository.start();
	
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		assertEquals(2, wfRepository.getAllWorkflowsInfos().size());
	}
	

}
