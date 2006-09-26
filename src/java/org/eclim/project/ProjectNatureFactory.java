/**
 * Copyright (c) 2005 - 2006
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
package org.eclim.project;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import org.eclipse.core.resources.IProjectNature;

/**
 * Factory for registering project natures.
 *
 * @author Eric Van Dewoestine (ervandew@yahoo.com)
 * @version $Revision$
 */
public class ProjectNatureFactory
{
  private static Map natureAliases = new HashMap();

  /**
   * Registers project natures separated by new lines.
   * <pre>
   *   alias nature
   * </pre>
   * <p/>
   * Ex.
   * <pre>
   *   java org.eclipse.jdt.core.javanature
   * </pre>
   *
   * @param _natures Natures to add.
   * @return the original natures string.
   */
  public static Object addNatures (String _natures)
  {
    String[] natures = StringUtils.split(_natures, '\n');
    for (int ii = 0; ii < natures.length; ii++){
      if(natures[ii].trim().length() > 0){
        String[] values = StringUtils.split(natures[ii].trim());
        if(values.length != 2){
          throw new RuntimeException(
              "Invalid nature definition: '" + natures[ii] + "'");
        }
        natureAliases.put(values[0], values[1]);
      }
    }

    return _natures;
  }

  /**
   * Gets array of registered nature aliases.
   *
   * @return Array of aliases.
   */
  public static String[] getNatureAliases ()
  {
    return (String[])
      natureAliases.keySet().toArray(ArrayUtils.EMPTY_STRING_ARRAY);
  }

  /**
   * Gets the nature string for the supplied alias.
   *
   * @param _alias The nature alias.
   * @return The nature or null if not found.
   */
  public static String getNatureForAlias (String _alias)
  {
    return (String)natureAliases.get(_alias);
  }
}
