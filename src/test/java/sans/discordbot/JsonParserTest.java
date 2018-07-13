package sans.discordbot;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import sans.discordbot.hearthstone.Card;
import sans.discordbot.league.Summoner;
import sans.discordbot.oxford.Definition;

public class JsonParserTest {

    @Test
    public void testGetDef() throws IOException {
        InputStream is = 
                IOUtils.toInputStream("{\r\n" + 
                        "  \"metadata\": {\r\n" + 
                        "    \"provider\": \"Oxford University Press\"\r\n" + 
                        "  },\r\n" + 
                        "  \"results\": [\r\n" + 
                        "    {\r\n" + 
                        "      \"id\": \"test\",\r\n" + 
                        "      \"language\": \"en\",\r\n" + 
                        "      \"lexicalEntries\": [\r\n" + 
                        "        {\r\n" + 
                        "          \"derivatives\": [\r\n" + 
                        "            {\r\n" + 
                        "              \"id\": \"testability\",\r\n" + 
                        "              \"text\": \"testability\"\r\n" + 
                        "            },\r\n" + 
                        "            {\r\n" + 
                        "              \"id\": \"testee\",\r\n" + 
                        "              \"text\": \"testee\"\r\n" + 
                        "            }\r\n" + 
                        "          ],\r\n" + 
                        "          \"entries\": [\r\n" + 
                        "            {\r\n" + 
                        "              \"etymologies\": [\r\n" + 
                        "                \"late Middle English (denoting a cupel used to treat gold or silver alloys or ore): via Old French from Latin testu, testum ‘earthen pot’, variant of testa ‘jug, shell’. Compare with test. The verb dates from the early 17th century\"\r\n" + 
                        "              ],\r\n" + 
                        "              \"grammaticalFeatures\": [\r\n" + 
                        "                {\r\n" + 
                        "                  \"text\": \"Singular\",\r\n" + 
                        "                  \"type\": \"Number\"\r\n" + 
                        "                }\r\n" + 
                        "              ],\r\n" + 
                        "              \"homographNumber\": \"100\",\r\n" + 
                        "              \"senses\": [\r\n" + 
                        "                {\r\n" + 
                        "                  \"definitions\": [\r\n" + 
                        "                    \"a procedure intended to establish the quality, performance, or reliability of something, especially before it is taken into widespread use\"\r\n" + 
                        "                  ],\r\n" + 
                        "                  \"examples\": [\r\n" + 
                        "                    {\r\n" + 
                        "                      \"text\": \"four fax modems are on test\"\r\n" + 
                        "                    },\r\n" + 
                        "                    {\r\n" + 
                        "                      \"text\": \"both countries carried out nuclear tests in May\"\r\n" + 
                        "                    }\r\n" + 
                        "                  ],\r\n" + 
                        "                  \"id\": \"m_en_gbus1042280.006\",\r\n" + 
                        "                  \"short_definitions\": [\r\n" + 
                        "                    \"procedure intended to establish quality, performance, or reliability of something\"\r\n" + 
                        "                  ],\r\n" + 
                        "                  \"subsenses\": [\r\n" + 
                        "                    {\r\n" + 
                        "                      \"definitions\": [\r\n" + 
                        "                        \"a short written or spoken examination of a person's proficiency or knowledge\"\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"examples\": [\r\n" + 
                        "                        {\r\n" + 
                        "                          \"text\": \"a spelling test\"\r\n" + 
                        "                        }\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"id\": \"m_en_gbus1042280.009\",\r\n" + 
                        "                      \"short_definitions\": [\r\n" + 
                        "                        \"short written or spoken examination of person's proficiency or knowledge\"\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"thesaurusLinks\": [\r\n" + 
                        "                        {\r\n" + 
                        "                          \"entry_id\": \"test\",\r\n" + 
                        "                          \"sense_id\": \"t_en_gb0014747.002\"\r\n" + 
                        "                        }\r\n" + 
                        "                      ]\r\n" + 
                        "                    },\r\n" + 
                        "                    {\r\n" + 
                        "                      \"definitions\": [\r\n" + 
                        "                        \"an event or situation that reveals the strength or quality of someone or something by putting them under strain\"\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"examples\": [\r\n" + 
                        "                        {\r\n" + 
                        "                          \"text\": \"this is the first serious test of the peace agreement\"\r\n" + 
                        "                        }\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"id\": \"m_en_gbus1042280.010\",\r\n" + 
                        "                      \"short_definitions\": [\r\n" + 
                        "                        \"event or situation that reveals strength or quality of person or thing by putting them under strain\"\r\n" + 
                        "                      ]\r\n" + 
                        "                    },\r\n" + 
                        "                    {\r\n" + 
                        "                      \"definitions\": [\r\n" + 
                        "                        \"an examination of part of the body or a body fluid for medical purposes, especially by means of a chemical or mechanical procedure rather than simple inspection\"\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"domains\": [\r\n" + 
                        "                        \"Medicine\"\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"examples\": [\r\n" + 
                        "                        {\r\n" + 
                        "                          \"text\": \"eye tests\"\r\n" + 
                        "                        },\r\n" + 
                        "                        {\r\n" + 
                        "                          \"text\": \"researchers developed a test for the virus\"\r\n" + 
                        "                        }\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"id\": \"m_en_gbus1042280.011\",\r\n" + 
                        "                      \"short_definitions\": [\r\n" + 
                        "                        \"examination of part of body or body fluid for medical purposes\"\r\n" + 
                        "                      ]\r\n" + 
                        "                    },\r\n" + 
                        "                    {\r\n" + 
                        "                      \"definitions\": [\r\n" + 
                        "                        \"a procedure employed to identify a substance or to reveal the presence or absence of a constituent within a substance.\"\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"domains\": [\r\n" + 
                        "                        \"Chemistry\"\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"id\": \"m_en_gbus1042280.012\",\r\n" + 
                        "                      \"short_definitions\": [\r\n" + 
                        "                        \"procedure employed to identify substance or to reveal presence or absence of constituent within substance\"\r\n" + 
                        "                      ]\r\n" + 
                        "                    },\r\n" + 
                        "                    {\r\n" + 
                        "                      \"definitions\": [\r\n" + 
                        "                        \"the result of a medical examination or analytical procedure\"\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"domains\": [\r\n" + 
                        "                        \"Medicine\"\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"examples\": [\r\n" + 
                        "                        {\r\n" + 
                        "                          \"text\": \"a positive test for protein\"\r\n" + 
                        "                        }\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"id\": \"m_en_gbus1042280.013\",\r\n" + 
                        "                      \"short_definitions\": [\r\n" + 
                        "                        \"result of medical examination or analytical procedure\"\r\n" + 
                        "                      ]\r\n" + 
                        "                    },\r\n" + 
                        "                    {\r\n" + 
                        "                      \"definitions\": [\r\n" + 
                        "                        \"a means of establishing whether an action, item, or situation is an instance of a specified quality, especially one held to be undesirable\"\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"examples\": [\r\n" + 
                        "                        {\r\n" + 
                        "                          \"text\": \"a statutory test of obscenity\"\r\n" + 
                        "                        }\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"id\": \"m_en_gbus1042280.014\",\r\n" + 
                        "                      \"short_definitions\": [\r\n" + 
                        "                        \"means of establishing whether action, item, or situation is instance of specified quality\"\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"thesaurusLinks\": [\r\n" + 
                        "                        {\r\n" + 
                        "                          \"entry_id\": \"test\",\r\n" + 
                        "                          \"sense_id\": \"t_en_gb0014747.003\"\r\n" + 
                        "                        }\r\n" + 
                        "                      ]\r\n" + 
                        "                    }\r\n" + 
                        "                  ],\r\n" + 
                        "                  \"thesaurusLinks\": [\r\n" + 
                        "                    {\r\n" + 
                        "                      \"entry_id\": \"test\",\r\n" + 
                        "                      \"sense_id\": \"t_en_gb0014747.001\"\r\n" + 
                        "                    }\r\n" + 
                        "                  ]\r\n" + 
                        "                },\r\n" + 
                        "                {\r\n" + 
                        "                  \"crossReferenceMarkers\": [\r\n" + 
                        "                    \"short for Test match\"\r\n" + 
                        "                  ],\r\n" + 
                        "                  \"crossReferences\": [\r\n" + 
                        "                    {\r\n" + 
                        "                      \"id\": \"test_match\",\r\n" + 
                        "                      \"text\": \"Test match\",\r\n" + 
                        "                      \"type\": \"abbreviation\"\r\n" + 
                        "                    }\r\n" + 
                        "                  ],\r\n" + 
                        "                  \"examples\": [\r\n" + 
                        "                    {\r\n" + 
                        "                      \"text\": \"the first Test against New Zealand\"\r\n" + 
                        "                    }\r\n" + 
                        "                  ],\r\n" + 
                        "                  \"id\": \"m_en_gbus1042280.016\",\r\n" + 
                        "                  \"variantForms\": [\r\n" + 
                        "                    {\r\n" + 
                        "                      \"text\": \"Test\"\r\n" + 
                        "                    }\r\n" + 
                        "                  ]\r\n" + 
                        "                },\r\n" + 
                        "                {\r\n" + 
                        "                  \"definitions\": [\r\n" + 
                        "                    \"a movable hearth in a reverberating furnace, used for separating gold or silver from lead.\"\r\n" + 
                        "                  ],\r\n" + 
                        "                  \"domains\": [\r\n" + 
                        "                    \"Metallurgy\"\r\n" + 
                        "                  ],\r\n" + 
                        "                  \"id\": \"m_en_gbus1042280.021\",\r\n" + 
                        "                  \"short_definitions\": [\r\n" + 
                        "                    \"movable hearth in reverberating furnace, used for separating gold or silver from lead\"\r\n" + 
                        "                  ]\r\n" + 
                        "                }\r\n" + 
                        "              ]\r\n" + 
                        "            },\r\n" + 
                        "            {\r\n" + 
                        "              \"etymologies\": [\r\n" + 
                        "                \"mid 19th century: from Latin testa ‘tile, jug, shell’. Compare with test\"\r\n" + 
                        "              ],\r\n" + 
                        "              \"grammaticalFeatures\": [\r\n" + 
                        "                {\r\n" + 
                        "                  \"text\": \"Singular\",\r\n" + 
                        "                  \"type\": \"Number\"\r\n" + 
                        "                }\r\n" + 
                        "              ],\r\n" + 
                        "              \"homographNumber\": \"200\",\r\n" + 
                        "              \"senses\": [\r\n" + 
                        "                {\r\n" + 
                        "                  \"definitions\": [\r\n" + 
                        "                    \"the shell or integument of some invertebrates and protozoans, especially the chalky shell of a foraminiferan or the tough outer layer of a tunicate.\"\r\n" + 
                        "                  ],\r\n" + 
                        "                  \"domains\": [\r\n" + 
                        "                    \"Zoology\"\r\n" + 
                        "                  ],\r\n" + 
                        "                  \"id\": \"m_en_gbus1042290.005\",\r\n" + 
                        "                  \"short_definitions\": [\r\n" + 
                        "                    \"shell or integument of some invertebrates and protozoans\"\r\n" + 
                        "                  ]\r\n" + 
                        "                }\r\n" + 
                        "              ]\r\n" + 
                        "            }\r\n" + 
                        "          ],\r\n" + 
                        "          \"language\": \"en\",\r\n" + 
                        "          \"lexicalCategory\": \"Noun\",\r\n" + 
                        "          \"pronunciations\": [\r\n" + 
                        "            {\r\n" + 
                        "              \"audioFile\": \"http://audio.oxforddictionaries.com/en/mp3/test_gb_1.mp3\",\r\n" + 
                        "              \"dialects\": [\r\n" + 
                        "                \"British English\"\r\n" + 
                        "              ],\r\n" + 
                        "              \"phoneticNotation\": \"IPA\",\r\n" + 
                        "              \"phoneticSpelling\": \"tɛst\"\r\n" + 
                        "            }\r\n" + 
                        "          ],\r\n" + 
                        "          \"text\": \"test\"\r\n" + 
                        "        },\r\n" + 
                        "        {\r\n" + 
                        "          \"derivatives\": [\r\n" + 
                        "            {\r\n" + 
                        "              \"id\": \"testee\",\r\n" + 
                        "              \"text\": \"testee\"\r\n" + 
                        "            },\r\n" + 
                        "            {\r\n" + 
                        "              \"id\": \"testability\",\r\n" + 
                        "              \"text\": \"testability\"\r\n" + 
                        "            }\r\n" + 
                        "          ],\r\n" + 
                        "          \"entries\": [\r\n" + 
                        "            {\r\n" + 
                        "              \"grammaticalFeatures\": [\r\n" + 
                        "                {\r\n" + 
                        "                  \"text\": \"Transitive\",\r\n" + 
                        "                  \"type\": \"Subcategorization\"\r\n" + 
                        "                },\r\n" + 
                        "                {\r\n" + 
                        "                  \"text\": \"Present\",\r\n" + 
                        "                  \"type\": \"Tense\"\r\n" + 
                        "                }\r\n" + 
                        "              ],\r\n" + 
                        "              \"homographNumber\": \"101\",\r\n" + 
                        "              \"senses\": [\r\n" + 
                        "                {\r\n" + 
                        "                  \"definitions\": [\r\n" + 
                        "                    \"take measures to check the quality, performance, or reliability of (something), especially before putting it into widespread use or practice\"\r\n" + 
                        "                  ],\r\n" + 
                        "                  \"domains\": [\r\n" + 
                        "                    \"Metallurgy\"\r\n" + 
                        "                  ],\r\n" + 
                        "                  \"examples\": [\r\n" + 
                        "                    {\r\n" + 
                        "                      \"text\": \"this range has not been tested on animals\"\r\n" + 
                        "                    },\r\n" + 
                        "                    {\r\n" + 
                        "                      \"text\": \"several trial runs were carried out to test the special brakes\"\r\n" + 
                        "                    }\r\n" + 
                        "                  ],\r\n" + 
                        "                  \"id\": \"m_en_gbus1042280.023\",\r\n" + 
                        "                  \"short_definitions\": [\r\n" + 
                        "                    \"take measures to check quality, performance, or reliability of something\"\r\n" + 
                        "                  ],\r\n" + 
                        "                  \"subsenses\": [\r\n" + 
                        "                    {\r\n" + 
                        "                      \"definitions\": [\r\n" + 
                        "                        \"give (someone) a short written or oral examination of their proficiency or knowledge\"\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"examples\": [\r\n" + 
                        "                        {\r\n" + 
                        "                          \"text\": \"all children are tested at eleven\"\r\n" + 
                        "                        }\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"id\": \"m_en_gbus1042280.029\",\r\n" + 
                        "                      \"short_definitions\": [\r\n" + 
                        "                        \"give someone short written or oral examination of their proficiency or knowledge\"\r\n" + 
                        "                      ]\r\n" + 
                        "                    },\r\n" + 
                        "                    {\r\n" + 
                        "                      \"definitions\": [\r\n" + 
                        "                        \"judge or measure (someone's proficiency or knowledge) by means of a test\"\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"examples\": [\r\n" + 
                        "                        {\r\n" + 
                        "                          \"text\": \"the exam will test accuracy and neatness\"\r\n" + 
                        "                        }\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"id\": \"m_en_gbus1042280.030\",\r\n" + 
                        "                      \"short_definitions\": [\r\n" + 
                        "                        \"judge or measure someone's proficiency or knowledge by means of test\"\r\n" + 
                        "                      ]\r\n" + 
                        "                    },\r\n" + 
                        "                    {\r\n" + 
                        "                      \"definitions\": [\r\n" + 
                        "                        \"reveal the strengths or capabilities of (someone or something) by putting them under strain\"\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"examples\": [\r\n" + 
                        "                        {\r\n" + 
                        "                          \"text\": \"such behaviour would severely test any marriage\"\r\n" + 
                        "                        }\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"id\": \"m_en_gbus1042280.031\",\r\n" + 
                        "                      \"short_definitions\": [\r\n" + 
                        "                        \"reveal strengths or capabilities of person or thing by putting them under strain\"\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"thesaurusLinks\": [\r\n" + 
                        "                        {\r\n" + 
                        "                          \"entry_id\": \"testing\",\r\n" + 
                        "                          \"sense_id\": \"t_en_gb0014753.001\"\r\n" + 
                        "                        },\r\n" + 
                        "                        {\r\n" + 
                        "                          \"entry_id\": \"test\",\r\n" + 
                        "                          \"sense_id\": \"t_en_gb0014747.005\"\r\n" + 
                        "                        }\r\n" + 
                        "                      ]\r\n" + 
                        "                    },\r\n" + 
                        "                    {\r\n" + 
                        "                      \"definitions\": [\r\n" + 
                        "                        \"carry out a medical test on (a person, a part of the body, or a body fluid)\"\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"domains\": [\r\n" + 
                        "                        \"Medicine\"\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"examples\": [\r\n" + 
                        "                        {\r\n" + 
                        "                          \"text\": \"he's been tested for drugs\"\r\n" + 
                        "                        }\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"id\": \"m_en_gbus1042280.032\",\r\n" + 
                        "                      \"short_definitions\": [\r\n" + 
                        "                        \"carry out medical test on\"\r\n" + 
                        "                      ]\r\n" + 
                        "                    },\r\n" + 
                        "                    {\r\n" + 
                        "                      \"definitions\": [\r\n" + 
                        "                        \"produce a specified result in a medical test, especially a drugs test or AIDS test\"\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"domains\": [\r\n" + 
                        "                        \"Medicine\"\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"examples\": [\r\n" + 
                        "                        {\r\n" + 
                        "                          \"text\": \"he tested positive for steroids during the race\"\r\n" + 
                        "                        }\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"id\": \"m_en_gbus1042280.033\",\r\n" + 
                        "                      \"notes\": [\r\n" + 
                        "                        {\r\n" + 
                        "                          \"text\": \"no object, with complement\",\r\n" + 
                        "                          \"type\": \"grammaticalNote\"\r\n" + 
                        "                        }\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"short_definitions\": [\r\n" + 
                        "                        \"produce specified result in medical test\"\r\n" + 
                        "                      ]\r\n" + 
                        "                    },\r\n" + 
                        "                    {\r\n" + 
                        "                      \"definitions\": [\r\n" + 
                        "                        \"examine (a substance) by means of a reagent.\"\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"domains\": [\r\n" + 
                        "                        \"Chemistry\"\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"id\": \"m_en_gbus1042280.034\",\r\n" + 
                        "                      \"short_definitions\": [\r\n" + 
                        "                        \"examine substance by means of reagent\"\r\n" + 
                        "                      ]\r\n" + 
                        "                    },\r\n" + 
                        "                    {\r\n" + 
                        "                      \"definitions\": [\r\n" + 
                        "                        \"touch or taste (something) to check that it is acceptable before proceeding further\"\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"examples\": [\r\n" + 
                        "                        {\r\n" + 
                        "                          \"text\": \"she tested the water with the tip of her elbow\"\r\n" + 
                        "                        }\r\n" + 
                        "                      ],\r\n" + 
                        "                      \"id\": \"m_en_gbus1042280.035\",\r\n" + 
                        "                      \"short_definitions\": [\r\n" + 
                        "                        \"touch or taste something to check that it is acceptable before proceeding further\"\r\n" + 
                        "                      ]\r\n" + 
                        "                    }\r\n" + 
                        "                  ],\r\n" + 
                        "                  \"thesaurusLinks\": [\r\n" + 
                        "                    {\r\n" + 
                        "                      \"entry_id\": \"test\",\r\n" + 
                        "                      \"sense_id\": \"t_en_gb0014747.004\"\r\n" + 
                        "                    }\r\n" + 
                        "                  ]\r\n" + 
                        "                }\r\n" + 
                        "              ]\r\n" + 
                        "            }\r\n" + 
                        "          ],\r\n" + 
                        "          \"language\": \"en\",\r\n" + 
                        "          \"lexicalCategory\": \"Verb\",\r\n" + 
                        "          \"pronunciations\": [\r\n" + 
                        "            {\r\n" + 
                        "              \"audioFile\": \"http://audio.oxforddictionaries.com/en/mp3/test_gb_1.mp3\",\r\n" + 
                        "              \"dialects\": [\r\n" + 
                        "                \"British English\"\r\n" + 
                        "              ],\r\n" + 
                        "              \"phoneticNotation\": \"IPA\",\r\n" + 
                        "              \"phoneticSpelling\": \"tɛst\"\r\n" + 
                        "            }\r\n" + 
                        "          ],\r\n" + 
                        "          \"text\": \"test\"\r\n" + 
                        "        }\r\n" + 
                        "      ],\r\n" + 
                        "      \"type\": \"headword\",\r\n" + 
                        "      \"word\": \"test\"\r\n" + 
                        "    }\r\n" + 
                        "  ]\r\n" + 
                        "}", "UTF-8");
        
        Definition def = JsonParser.getDef(is);
        assertEquals("test", def.getWord());
        assertEquals("Verb", def.getEntries().get(1).getLexCat());
        assertEquals("take measures to check the quality, performance, or reliability of (something), especially before putting it into widespread use or practice", def.getEntries().get(1).getDefs().get(0));
        
        
    }

    @Test
    public void testGetCard() throws IOException {
        InputStream is = IOUtils.toInputStream("[\r\n" + 
                "  {\r\n" + 
                "    \"cardId\": \"KAR_061\",\r\n" + 
                "    \"dbfId\": \"39225\",\r\n" + 
                "    \"name\": \"The Curator\",\r\n" + 
                "    \"cardSet\": \"One Night in Karazhan\",\r\n" + 
                "    \"type\": \"Minion\",\r\n" + 
                "    \"rarity\": \"Legendary\",\r\n" + 
                "    \"cost\": 7,\r\n" + 
                "    \"attack\": 4,\r\n" + 
                "    \"health\": 6,\r\n" + 
                "    \"text\": \"<b>Taunt</b>\\\\n<b>Battlecry:</b> Draw a Beast, Dragon, and Murloc from your deck.\",\r\n" + 
                "    \"flavor\": \"The Curator guards Azeroth’s deadliest creatures, but it’s secretly terrified of squirrels.\",\r\n" + 
                "    \"artist\": \"Wei Wang\",\r\n" + 
                "    \"collectible\": true,\r\n" + 
                "    \"elite\": true,\r\n" + 
                "    \"race\": \"Mech\",\r\n" + 
                "    \"playerClass\": \"Neutral\",\r\n" + 
                "    \"howToGet\": \"Unlocked in the Menagerie, in One Night in Karazhan.\",\r\n" + 
                "    \"howToGetGold\": \"Crafting unlocked in the Menagerie, in One Night in Karazhan.\",\r\n" + 
                "    \"img\": \"http://media.services.zam.com/v1/media/byName/hs/cards/enus/KAR_061.png\",\r\n" + 
                "    \"imgGold\": \"http://media.services.zam.com/v1/media/byName/hs/cards/enus/animated/KAR_061_premium.gif\",\r\n" + 
                "    \"locale\": \"enUS\",\r\n" + 
                "    \"mechanics\": [\r\n" + 
                "      {\r\n" + 
                "        \"name\": \"Battlecry\"\r\n" + 
                "      },\r\n" + 
                "      {\r\n" + 
                "        \"name\": \"Taunt\"\r\n" + 
                "      }\r\n" + 
                "    ]\r\n" + 
                "  }\r\n" + 
                "]", "UTF-8");
        Card c = JsonParser.getCard(is);
        assertEquals("Wei Wang", c.getArtist());
        assertEquals("The Curator guards Azeroth’s deadliest creatures, but it’s secretly terrified of squirrels.", c.getFlavor());
    }

    @Test
    public void testGetSpells() throws IOException {
        InputStream is = HttpRequest.sendGet("http://ddragon.leagueoflegends.com/cdn/8.9.1/data/en_US/champion/Morgana.json");
        String s = JsonParser.getSpells(is, "Morgana");
        assertEquals("```python\n" + "Morgana Cooldowns\n" + "Q: 11\n" + "W: 12\n" + "E: 23/21/19/17/15\n" + "R: 120/110/100\n" + "```" , s);
    }

    @Test
    public void testGetSummonerInfo() throws IOException {
        InputStream is = IOUtils.toInputStream("{\r\n" + 
                "    \"profileIconId\": 3170,\r\n" + 
                "    \"name\": \"SansNickel\",\r\n" + 
                "    \"summonerLevel\": 88,\r\n" + 
                "    \"accountId\": 217799373,\r\n" + 
                "    \"id\": 55932050,\r\n" + 
                "    \"revisionDate\": 1525322916000\r\n" + 
                "}", "UTF-8");
        Summoner s = JsonParser.getSummonerInfo(is);
        assertEquals(88, s.getLevel());
        assertEquals(55932050, s.getId());
        assertEquals("SansNickel", s.getName());
    }

    @Test
    public void testGetSummonerSoloStats() throws IOException {
        InputStream is = IOUtils.toInputStream("{\r\n" + 
                "    \"profileIconId\": 3170,\r\n" + 
                "    \"name\": \"SansNickel\",\r\n" + 
                "    \"summonerLevel\": 88,\r\n" + 
                "    \"accountId\": 217799373,\r\n" + 
                "    \"id\": 55932050,\r\n" + 
                "    \"revisionDate\": 1525322916000\r\n" + 
                "}", "UTF-8");
        Summoner s = JsonParser.getSummonerInfo(is);
        InputStream is2 = IOUtils.toInputStream("[\r\n" + 
                "    {\r\n" + 
                "        \"queueType\": \"RANKED_SOLO_5x5\",\r\n" + 
                "        \"hotStreak\": true,\r\n" + 
                "        \"wins\": 21,\r\n" + 
                "        \"veteran\": false,\r\n" + 
                "        \"losses\": 12,\r\n" + 
                "        \"playerOrTeamId\": \"55932050\",\r\n" + 
                "        \"leagueName\": \"Draven's Warlords\",\r\n" + 
                "        \"playerOrTeamName\": \"SansNickel\",\r\n" + 
                "        \"inactive\": false,\r\n" + 
                "        \"rank\": \"V\",\r\n" + 
                "        \"freshBlood\": false,\r\n" + 
                "        \"leagueId\": \"a59b48e0-ffcf-11e7-bc10-c81f66cf135e\",\r\n" + 
                "        \"tier\": \"DIAMOND\",\r\n" + 
                "        \"leaguePoints\": 0\r\n" + 
                "    }\r\n" + 
                "]", "UTF-8");
        Summoner s2 = JsonParser.getSummonerSoloStats(s, is2);
        assertEquals("DIAMOND V", s2.getRank());
        assertEquals(64, s2.getWinRate());
    }

    @Test
    public void testGetRuneName() {
        String name = JsonParser.getRuneName(8453);
        assertEquals("Revitalize", name);
        name = JsonParser.getRuneName(2538);
        assertEquals("", name);
    }

  
    @Test
    public void testGetPatchNo() throws IOException {
        InputStream is = HttpRequest.sendGet("https://ddragon.leagueoflegends.com/api/versions.json");
        String response = JsonParser.getPatchNo(is);
        System.out.println(response);
        assertTrue(response != null);
    }

    @Test
    public void testToOutputBlock() {
        String s = JsonParser.toOutputBlock("hello", "ml");
        assertEquals("```ml\nhello```", s);
    }

}
