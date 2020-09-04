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
				<fo:simple-page-master master-name="sciPage"
					page-height="29.7cm" page-width="21cm" margin-top="1cm"
					margin-bottom="2cm" margin-left="2.5cm" margin-right="2.5cm">
					<fo:region-body margin-top="2cm" margin-bottom="2cm" />
					<fo:region-before extent="2cm" />
					<fo:region-after extent="2cm" />
				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="sciPage">
				<fo:flow flow-name="xsl-region-body" font-size="12px"
					font-family="Times" text-align="justify">
					<fo:block text-align="center" font-size="22px"
						space-after="16px">
						<xsl:value-of select="ns:review/ns:paperTitle" />
					</fo:block>
					<fo:block space-after="5px">
						Reviewer:
						Full Name:
						<xsl:value-of
							select="ns:review/ns:reviewer/ns:fullName"></xsl:value-of>
					</fo:block>
					<fo:block>
					Institution Name:
					<xsl:value-of select="ns:review/ns:reviewer/ns:institution/ns:name"></xsl:value-of>
					</fo:block>
					
					<fo:block space-after="5px">
					Institution Address:
					<xsl:value-of select="ns:review/ns:reviewer/ns:institution/ns:location/ns:City"></xsl:value-of>, 
					<xsl:value-of select="ns:review/ns:reviewer/ns:institution/ns:location/ns:State"></xsl:value-of>, 
					<xsl:value-of select="ns:review/ns:reviewer/ns:institution/ns:location/ns:Country"></xsl:value-of>
					</fo:block>
					
					<fo:block space-after="5px">
					Email:
					<xsl:value-of select="ns:review/ns:reviewer/ns:email"></xsl:value-of>
					</fo:block>
					
					<fo:block space-after="5px">
					Comments:
						<xsl:for-each select="ns:review/ns:comments">
							<xsl:value-of select="ns:element-id"> </xsl:value-of>
							<xsl:value-of select="ns:comment"></xsl:value-of>
						</xsl:for-each>
						
					</fo:block>
					
					<fo:block space-after="5px">
					Summary:
						<xsl:value-of select="ns:review/ns:summary"></xsl:value-of>
					</fo:block>
					
					<fo:block space-after="5px">
					Grade:
						<xsl:value-of select="ns:review/ns:grade"></xsl:value-of>
					</fo:block>



				</fo:flow>
			</fo:page-sequence>

		</fo:root>
	</xsl:template>

</xsl:stylesheet>