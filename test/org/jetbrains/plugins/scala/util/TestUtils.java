/*
 * Copyright 2000-2008 JetBrains s.r.o.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.plugins.scala.util;

import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiFileFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.LocalTimeCounter;

import java.net.URL;
import java.net.URISyntaxException;
import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: Ilya.Sergey
 */
public class TestUtils {
  private static final Logger LOG = Logger.getInstance("org.jetbrains.plugins.scala.util.TestUtils");

  public static PsiFile createPseudoPhysicalFile(final Project project, final String text, int i) throws IncorrectOperationException {
    String TEMP_FILE = project.getProjectFilePath() + "temp" + i + ".scala";
    return PsiFileFactory.getInstance(project).createFileFromText(
        TEMP_FILE,
        FileTypeManager.getInstance().getFileTypeByFileName(TEMP_FILE),
        text,
        LocalTimeCounter.currentTime(),
        true);
  }


  public static PsiFile createPseudoPhysicalFile(final Project project, final String text) throws IncorrectOperationException {
    String TEMP_FILE = project.getProjectFilePath() + "temp.scala";
    return PsiFileFactory.getInstance(project).createFileFromText(
        TEMP_FILE,
        FileTypeManager.getInstance().getFileTypeByFileName(TEMP_FILE),
        text,
        LocalTimeCounter.currentTime(),
        true);
  }

  private static String TEST_DATA_PATH = null;

  public static String getTestDataPath() {
    if (TEST_DATA_PATH == null) {
      ClassLoader loader = TestUtils.class.getClassLoader();
      URL resource = loader.getResource("testdata");
      try {
        TEST_DATA_PATH = new File(resource.toURI()).getPath().replace(File.separatorChar, '/');
      } catch (URISyntaxException e) {
        LOG.error(e);
        return null;
      }
    }

    return TEST_DATA_PATH;
  }


}
