package Algorithm_Component;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import ess.algorithm.RoemischerVerbund;
import ess.algorithm.RoemischerVerbund.Validation;

public class API_Test_ValidationV2 {

    /*
     * Prüft, ob die neue Validierungsbedingung NICHT_GENUTZTE_FLIESE korrekt implementiert ist
     * 
     * Der Test übergibt eine absichtlich falsche Lösung zur Validierung
     */
    @Test
    public void validateSolutionV2Failure() throws IOException {
        
        // Arrange (set all necessary preconditions and inputs.)
        RoemischerVerbund api = new RoemischerVerbund();
        
        // Schreibt die Probleminstanz in ein temporäres Verzeichnis für diesen Testlauf
        String filePath = WriteXmlToTempDirectory(t1);
        
        //Act (on the object or method under test.)
        List<Validation> errorList = api.validateSolution(filePath, 80);
        
        //Assert (that the expected results have occurred.))
        assertTrue("errorList ist null", errorList != null);
        assertTrue("Fließe austauschbar nicht erkannt", errorList.contains(Validation.FLIESEN_AUSTAUSCHBAR));
        assertTrue("Maximale Fugenlänge nicht erkannt", errorList.contains(Validation.MAX_FUGENLAENGE));
        assertTrue("Nicht genutzte Fliese nicht erkannt", errorList.contains(Validation.NICHT_GENUTZTE_FLIESE));
        assertTrue("Es wurden nicht alle bzw. nicht die korrekten Fehler erkannt", errorList.size() == 3);
    }
    
    /*
     * Prüft, ob die neue Validierungsbedingung NICHT_GENUTZTE_FLIESE korrekt implementiert ist
     * 
     * Der Test übergibt eine valide Lösung zur Validierung
     */
    @Test
    public void validateSolutionV2Valid() throws IOException {
        
        // Arrange (set all necessary preconditions and inputs.)
        RoemischerVerbund api = new RoemischerVerbund();
        
        // Schreibt die Probleminstanz in ein temporäres Verzeichnis für diesen Testlauf
        String filePath = WriteXmlToTempDirectory(t1_ok);
        
        //Act (on the object or method under test.)
        List<Validation> errorList = api.validateSolution(filePath, 80);
        
        //Assert (that the expected results have occurred.))
        assertTrue("errorList ist null", errorList != null);
        
        assertTrue("Fließe austauschbar nicht erkannt", errorList.contains(Validation.FLIESEN_AUSTAUSCHBAR));
        assertTrue("Maximale Fugenlänge nicht erkannt", errorList.contains(Validation.MAX_FUGENLAENGE));
        assertTrue("Es wurden nicht alle bzw. nicht die korrekten Fehler erkannt",errorList.size() == 2);
    }
    
    @Rule
    public TemporaryFolder folder= new TemporaryFolder();
    
    /**
     * Die Probleminstanzen müssen zwischengespeichert werden, da das XML-Dokument durch die API modifiziert wird.
     * Das ist nötig, damit beim erneuten Durchführen die Instanzen unmodifiziert sind.
     *  
     * @param fileName Dateiname, der temporär zwischengespeichert werden soll
     * @return temporärer Pfad 
     * @throws IOException 
     */
    private String WriteXmlToTempDirectory(String xmlDocument) throws IOException
    {
        WriteDtdFileToTempDirectory("instances/DataModel.dtd");
        // In Unit-Tests brauch kein fortgeschrittenes Exceptionhandling integriert werden.
        // Der Grund dafür ist, dass der Test genau ein Szenario abbildet.
        byte[] bytes = xmlDocument.getBytes();
        
        String xmlAsString = new String(bytes, "UTF8");         
        String tempFileName = String.format("%s.xml" ,UUID.randomUUID().toString());
        File testFile = folder.newFile(tempFileName);

        FileWriter fw = null;
        try {
            fw = new FileWriter(testFile);
            fw.write(xmlAsString);
        } finally {
            if(fw != null) {
                fw.close();
            }
        }   
        
        return testFile.getPath();
    }
    
    /*
     * 
     */
    private void WriteDtdFileToTempDirectory(String fileName) throws IOException {

        byte[] bytes = Files.readAllBytes(Paths.get(fileName));
        String xmlAsString = new String(bytes, "UTF8");
        File testFile = folder.newFile("DataModel.dtd");

        FileWriter fw = null;
        try {
            fw = new FileWriter(testFile);
            fw.write(xmlAsString);
        } finally {
            if (fw != null) {
                fw.close();
            }
        }
    }   
    
    final String t1 ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
            "<RoemischerVerbund length1=\"440\" length2=\"480\">\r\n" + 
            "  <Fliesentypen>\r\n" + 
            "    <Fliesentyp ident=\"_0\">\r\n" + 
            "      <length1>40</length1>\r\n" + 
            "      <length2>60</length2>\r\n" + 
            "    </Fliesentyp>\r\n" + 
            "    <Fliesentyp ident=\"_1\">\r\n" + 
            "      <length1>60</length1>\r\n" + 
            "      <length2>40</length2>\r\n" + 
            "    </Fliesentyp>\r\n" + 
            "    <Fliesentyp ident=\"_2\">\r\n" + 
            "      <length1>20</length1>\r\n" + 
            "      <length2>40</length2>\r\n" + 
            "    </Fliesentyp>\r\n" + 
            "    <Fliesentyp ident=\"_3\">\r\n" + 
            "      <length1>40</length1>\r\n" + 
            "      <length2>20</length2>\r\n" + 
            "    </Fliesentyp>\r\n" + 
            "    <Fliesentyp ident=\"_4\">\r\n" + 
            "      <length1>40</length1>\r\n" + 
            "      <length2>40</length2>\r\n" + 
            "    </Fliesentyp>\r\n" + 
            "   <Fliesentyp ident=\"_5\">\r\n" + 
            "      <length1>120</length1>\r\n" + 
            "      <length2>120</length2>\r\n" + 
            "    </Fliesentyp>\r\n" + 
            "  </Fliesentypen>\r\n" + 
            "  <Verlegungsplan>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>0</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>1</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>2</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>3</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>4</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>5</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>6</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>7</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>8</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>9</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>10</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>11</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>12</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>13</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>14</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>15</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>16</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>17</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>18</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>19</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>20</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>21</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>22</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>23</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>24</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>25</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>26</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>27</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>28</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>29</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>30</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>31</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>32</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>33</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>34</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>35</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>36</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>37</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>38</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>39</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>40</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>41</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>42</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>43</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>44</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>45</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>46</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>47</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>48</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>49</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>50</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>51</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>52</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>53</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>54</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>55</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>56</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>57</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>58</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>59</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>60</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>61</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>62</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>63</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>64</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>65</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>66</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>67</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>68</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>69</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>70</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>71</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>72</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>73</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>74</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>75</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>76</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>77</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>78</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>79</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>80</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>81</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>82</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>83</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>84</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>85</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>86</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>87</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>88</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>89</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>90</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>91</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>92</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>93</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>94</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>95</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>96</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>97</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>98</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>99</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>100</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>101</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>102</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>103</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>104</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>105</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>106</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>107</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>108</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>109</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>110</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>111</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>112</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>113</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>114</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>115</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>116</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>117</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>118</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>119</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>120</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>121</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>122</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>123</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>124</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>125</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>126</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>127</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>128</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>129</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>130</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>131</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>132</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>133</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>134</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>135</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>136</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>137</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>138</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>139</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>140</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>141</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>142</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>143</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "  </Verlegungsplan>\r\n" + 
            "</RoemischerVerbund>\r\n" + 
            "";
    
    final String t1_ok = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
            "<RoemischerVerbund length1=\"440\" length2=\"480\">\r\n" + 
            "  <Fliesentypen>\r\n" + 
            "    <Fliesentyp ident=\"_0\">\r\n" + 
            "      <length1>40</length1>\r\n" + 
            "      <length2>60</length2>\r\n" + 
            "    </Fliesentyp>\r\n" + 
            "    <Fliesentyp ident=\"_1\">\r\n" + 
            "      <length1>60</length1>\r\n" + 
            "      <length2>40</length2>\r\n" + 
            "    </Fliesentyp>\r\n" + 
            "    <Fliesentyp ident=\"_2\">\r\n" + 
            "      <length1>20</length1>\r\n" + 
            "      <length2>40</length2>\r\n" + 
            "    </Fliesentyp>\r\n" + 
            "    <Fliesentyp ident=\"_3\">\r\n" + 
            "      <length1>40</length1>\r\n" + 
            "      <length2>20</length2>\r\n" + 
            "    </Fliesentyp>\r\n" + 
            "    <Fliesentyp ident=\"_4\">\r\n" + 
            "      <length1>40</length1>\r\n" + 
            "      <length2>40</length2>\r\n" + 
            "    </Fliesentyp>\r\n" + 
            "  </Fliesentypen>\r\n" + 
            "  <Verlegungsplan>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>0</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>1</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>2</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>3</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>4</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>5</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>6</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>7</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>8</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>9</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>10</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>11</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>12</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>13</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>14</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>15</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>16</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>17</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>18</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>19</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>20</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>21</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>22</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>23</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>24</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>25</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>26</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>27</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>28</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>29</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>30</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>31</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>32</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>33</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>34</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>35</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>36</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>37</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>38</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>39</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>40</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>41</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>42</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>43</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>44</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>45</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>46</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>47</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>48</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>49</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>50</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>51</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>52</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>53</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>54</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>55</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>56</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>57</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>58</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>59</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>60</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>61</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>62</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>63</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>64</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>65</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>66</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>67</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>68</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>69</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>70</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>71</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>72</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>73</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>74</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>75</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>76</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>77</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>78</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>79</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>80</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>81</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>82</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>83</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>84</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>85</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>86</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>87</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>88</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>89</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>90</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>91</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>92</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>93</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>94</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>95</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>96</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>97</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>98</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>99</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>100</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>101</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>102</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>103</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>104</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>105</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>106</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>107</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>108</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>109</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>110</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>111</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>112</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>113</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>114</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>115</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>116</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>117</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>118</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>119</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>120</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>121</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>122</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>123</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>124</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>125</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>126</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>127</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>128</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>129</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>130</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>131</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>132</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>133</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_0\">\r\n" + 
            "      <Nr>134</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>135</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>136</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>137</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>138</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_2\">\r\n" + 
            "      <Nr>139</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_4\">\r\n" + 
            "      <Nr>140</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_1\">\r\n" + 
            "      <Nr>141</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>142</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "    <Platte fliesenId=\"_3\">\r\n" + 
            "      <Nr>143</Nr>\r\n" + 
            "    </Platte>\r\n" + 
            "  </Verlegungsplan>\r\n" + 
            "</RoemischerVerbund>\r\n" + 
            "";
    
}
