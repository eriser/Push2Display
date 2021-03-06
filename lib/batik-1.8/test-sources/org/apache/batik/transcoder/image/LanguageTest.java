/*

   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */
package org.apache.batik.transcoder.image;

import java.util.HashMap;
import java.util.Map;

import org.apache.batik.transcoder.TranscoderInput;

/**
 * Test the ImageTranscoder with the KEY_LANGUAGE transcoding hint.
 *
 * @author <a href="mailto:Thierry.Kormann@sophia.inria.fr">Thierry Kormann</a>
 * @version $Id: LanguageTest.java 1372129 2012-08-12 15:31:50Z helder $ 
 */
public class LanguageTest extends AbstractImageTranscoderTest {

    /**
     * Constructs a new <code>LanguageTest</code>.
     */
    /** The URI of the input image. */
    protected String inputURI;

    /** The URI of the reference image. */
    protected String refImageURI;

    /** The preferred language. */
    protected String language;

    /**
     * Constructs a new <code>LanguageTest</code>.
     *
     * @param inputURI the URI of the input image
     * @param refImageURI the URI of the reference image
     * @param language the preferred language
     */
    public LanguageTest(String inputURI, 
                        String refImageURI, 
                        String language) {
        this.inputURI = inputURI;
        this.refImageURI = refImageURI;
        this.language = language;
    }

    /**
     * Creates the <code>TranscoderInput</code>.
     */
    protected TranscoderInput createTranscoderInput() {
        return new TranscoderInput(resolveURL(inputURI).toString());
    }
    
    /**
     * Creates a Map that contains additional transcoding hints.
     */
    protected Map createTranscodingHints() {
        Map hints = new HashMap(7);
        hints.put(ImageTranscoder.KEY_LANGUAGE, language);
        return hints;
    }

    /**
     * Returns the reference image for this test.
     */
    protected byte [] getReferenceImageData() {
        return createBufferedImageData(resolveURL(refImageURI));
    }
}
