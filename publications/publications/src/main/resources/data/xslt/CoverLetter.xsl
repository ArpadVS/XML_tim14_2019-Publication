<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:ns1="http://www.ftn.uns.ac.rs/tim14"
    xmlns:ns2="https://github.com/ArpadVS/XML_tim14_2019-Publication"
    exclude-result-prefixes="xs"
    version="2.0">
    <xsl:template match="/">
        <html>
            <head>
                
                <title>COVER LETTER</title>
            </head>
            <body>
                
                <h1>Cover Letter</h1>
                <p><b>Sender Contact:</b> 
                    <br/>Name: 
                    <xsl:value-of select="ns2:Cover_letter/ns2:sender_contact/ns2:Name"/>
                    <br/>Phone number: 
                    <xsl:value-of select="ns2:Cover_letter/ns2:sender_contact/ns2:Phone_number"/>
                    <br/>Location: 
                    <xsl:value-of select="ns2:Cover_letter/ns2:sender_contact/ns2:Location"/>
                    <br/>Email: 
                    <xsl:value-of select="ns2:Cover_letter/ns2:sender_contact/ns2:Email"/>
                    
                </p>
                
                <p><b>Employer Contact:</b> 
                    <br/>Name: 
                    <xsl:value-of select="ns2:Cover_letter/ns2:employer_contact/ns2:Name"/>
                    <br/>Phone number: 
                    <xsl:value-of select="ns2:Cover_letter/ns2:employer_contact/ns2:Phone_number"/>
                    <br/>Location: 
                    <xsl:value-of select="ns2:Cover_letter/ns2:employer_contact/ns2:Location"/>
                    <br/>Email: 
                    <xsl:value-of select="ns2:Cover_letter/ns2:employer_contact/ns2:Email"/>
                    
                </p>
                
                <p><b>Body:</b> 
                    <br/>Paragraphs: 
                    <xsl:value-of select="ns2:Cover_letter/ns2:body/ns2:Paragraphs/ns2:quote"/>
                    <br/>Thanksgiving: 
                    <xsl:value-of select="ns2:Cover_letter/ns2:body/ns2:Thanksgiving"/>
                    <br/><br/><b>Closure: </b>
                    <br/>
                    <xsl:value-of select="ns2:Cover_letter/ns2:body/ns2:Closure/ns2:Compliment"/>
                    <br/>
                    <xsl:value-of select="ns2:Cover_letter/ns2:body/ns2:Closure/ns2:Signature"/>
                    
                    
                </p>
            </body>
        </html>
    </xsl:template>
    
</xsl:stylesheet>