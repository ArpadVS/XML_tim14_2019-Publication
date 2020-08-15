<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:ns="http://www.ftn.uns.ac.rs/tim14"
    version="2.0">
    <xsl:template match="/">
        <html>
            <head>
                
                <title>USER</title>
            </head>
            <body>
                
                <h1>User</h1>
                <p>First name: 
                    <xsl:value-of select="ns:user/ns:first_name"/>
                    <br/>Last name: 
                    <xsl:value-of select="ns:user/ns:last_name"/>
                    <br/>
                    Roles:
                    <xsl:value-of select="ns:user/ns:role"/>
                    <br/>
                    Expertise: 
                    <xsl:value-of select="ns:user/ns:expertise"/>
                </p>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>