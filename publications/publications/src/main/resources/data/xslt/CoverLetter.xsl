<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:ns1="http://www.ftn.uns.ac.rs/tim14"
    xmlns:ns="https://github.com/ArpadVS/XML_tim14_2019-Publication"
    exclude-result-prefixes="xs"
    version="2.0">
    <xsl:template match="/">
        <html>
            <head>
                
                <title>COVER LETTER</title>
                <style type="text/css">
					.tab {margin-left:40px;
					margin-top:0px;};
					<style type="text/css">
                    body {
                        font-family: sans-serif;
                    }
                    
                    .bold {
                        font-weight: bold;
                    }
                    
                    .italic {
                        font-style: italic;
                    }
                    
                    .align-right {
                        text-align: right;
                    }
                    
                    .align-center {
                        text-align: center;
                    }
                    
                    .align-justify {
                        text-align: justify:
                    }
                    
                    .center-content {
                        margin: auto;
                        width: 750px;
                    }
                </style>
				</style>
            </head>
            <body>
                <div class="center-content">
                	<h1 class="align-center"> Cover Letter </h1>
                    <table style="width:100%;margin-bottom:30px;margin-top:40px;">
                        <tr style="margin-bottom:5px;">
                            <td class="bold">Sender</td>
                            <td class="bold align-right">Receiver</td>
                        </tr>
                        <tr>
                            <td><xsl:value-of select="ns:Cover_letter/ns:sender_contact/ns:Name"/></td>
                            <td class="align-right"><xsl:value-of select="ns:Cover_letter/ns:employer_contact/ns:Name"/></td>
                        </tr>
                        <tr>
                            <td><xsl:value-of select="ns:Cover_letter/ns:sender_contact/ns:Location"/> </td>
                            <td class="align-right"><xsl:value-of select="ns:Cover_letter/ns:employer_contact/ns:Location"/></td>
                        </tr>
                        <tr>
                            <td><xsl:value-of select="ns:Cover_letter/ns:sender_contact/ns:Phone_number"/></td>
                            <td class="align-right"><xsl:value-of select="ns:Cover_letter/ns:employer_contact/ns:Phone_number"/></td>
                        </tr>
                        <tr>
                            <td><xsl:value-of select="ns:Cover_letter/ns:sender_contact/ns:Email"/></td>
                            <td class="align-right"><xsl:value-of select="ns:Cover_letter/ns:employer_contact/ns:Email"/></td>
                        </tr>
                    </table>
                    <p style="margin-bottom: 35px;"><xsl:value-of select="ns:Cover_letter/ns:body/ns:Thanksgiving" /></p>
                    <xsl:for-each select="ns:Cover_letter/ns:body/ns:Paragraphs">
                        <p style="margin-bottom: 10px;" class="align-justify">
                            <xsl:value-of select="."></xsl:value-of>
                        </p>
                    </xsl:for-each>
                    
                    <div style="margin-bottom:30px; margin-top: 30px;" margin-bottom="30px" margin-top="30px">
                        <p style="margin-bottom: 5px;">
                            <xsl:value-of select="ns:Cover_letter/ns:body/ns:Closure/ns:Compliment"></xsl:value-of>
                        </p>
                        <p style="margin-bottom: 5px;">
                            <xsl:value-of select="ns:Cover_letter/ns:body/ns:Closure/ns:Signature"></xsl:value-of>
                        </p>
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>
    
</xsl:stylesheet>