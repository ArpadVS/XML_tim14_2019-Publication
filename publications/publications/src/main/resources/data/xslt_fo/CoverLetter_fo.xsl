<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	xmlns:ns="https://github.com/ArpadVS/XML_tim14_2019-Publication"
	exclude-result-prefixes="xs" version="2.0">
	<xsl:template match="/">
		<fo:root>
			 <fo:layout-master-set>
                <fo:simple-page-master master-name="cover-letter-page">
                    <fo:region-body margin="1in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="cover-letter-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-size="20px" font-weight="bold" text-align="center" space-after="30px"> Cover Letter</fo:block>
                    <fo:table margin-bottom="30px">
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell>
                                    <!-- Sender contact -->
                                    <fo:block>
                                        <fo:block margin-bottom="5px" font-weight="bold">Sender</fo:block>
                                        <fo:block>
                                            <xsl:value-of select="ns:Cover_letter/ns:sender_contact/ns:Name"/>
                                        </fo:block>
                                        <fo:block>
                                            <xsl:if test="ns:Cover_letter/ns:sender_contact/ns:Location">
                                                <xsl:value-of select="ns:Cover_letter/ns:sender_contact/ns:Location"/>
                                            </xsl:if>
                                        </fo:block>
                                        <fo:block>
                                            <xsl:value-of select="ns:Cover_letter/ns:sender_contact/ns:Phone_number" />,
                                        </fo:block>
                                        <fo:block>
                                            <xsl:value-of select="ns:Cover_letter/ns:sender_contact/ns:Email"/>
                                        </fo:block>
                                    </fo:block>
                                </fo:table-cell>
                                
                                 <fo:table-cell>
                                    <!-- Receiver contact -->
                                    <fo:block text-align="right">
                                        <fo:block margin-bottom="5px" font-weight="bold" >Receiver</fo:block>
                                        <fo:block>
                                            <xsl:value-of select="ns:Cover_letter/ns:employer_contact/ns:Name"/>
                                        </fo:block>
                                        <fo:block>
                                            <xsl:if test="ns:Cover_letter/ns:employer_contact/ns:Location">
                                                <xsl:value-of select="ns:Cover_letter/ns:employer_contact/ns:Location"/>
                                            </xsl:if>
                                        </fo:block>
                                        <fo:block>
                                            <xsl:value-of select="ns:Cover_letter/ns:employer_contact/ns:Phone_number" />,
                                        </fo:block>
                                        <fo:block>
                                            <xsl:value-of select="ns:Cover_letter/ns:employer_contact/ns:Email"/>
                                        </fo:block>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                    
                    <fo:block margin-bottom="5px">
                        <xsl:value-of select="ns:Cover_letter/ns:body/ns:Thanksgiving"></xsl:value-of>
                    </fo:block>
                    <fo:block margin-bottom="30px" margin-top="30px">
                        
                        <xsl:for-each select="ns:Cover_letter/ns:body/ns:Paragraphs">
                            <fo:block margin-bottom="10px" text-align="justify">
                                <xsl:apply-templates/>
                            </fo:block>
                        </xsl:for-each>
                    </fo:block>
                    
                    <fo:block margin-bottom="30px" margin-top="30px">
                        <fo:block margin-bottom="5px">
                            <xsl:value-of select="ns:Cover_letter/ns:body/ns:Closure/ns:Compliment"></xsl:value-of>
                        </fo:block>
                        <fo:block margin-bottom="5px">
                            <xsl:value-of select="ns:Cover_letter/ns:body/ns:Closure/ns:Signature"></xsl:value-of>
                        </fo:block>
                    </fo:block>
                </fo:flow>
                
            </fo:page-sequence>
		</fo:root>
	</xsl:template>
	
</xsl:stylesheet>