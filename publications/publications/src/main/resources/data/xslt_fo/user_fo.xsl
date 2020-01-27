<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:ns="http://www.ftn.uns.ac.rs/tim14"
    xmlns:fo="http://www.w3.org/1999/XSL/Format"
    version="2.0">
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="user-page">
                    <fo:region-body/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="user-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-family="sans-serif" font-size="24px" font-weight="bold" padding="10px">
                        USER
                    </fo:block>
                    <fo:block text-indent="10px">
                        First name: 
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="ns:user/ns:first_name"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block text-indent="10px">
                        Last name: 
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="ns:user/ns:last_name"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block text-indent="10px">
                        Roles: 
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="ns:user/ns:role"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block text-indent="10px">
                        Expertise: 
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="ns:user/ns:expertise"/>
                        </fo:inline>
                    </fo:block>
                </fo:flow>
                
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>