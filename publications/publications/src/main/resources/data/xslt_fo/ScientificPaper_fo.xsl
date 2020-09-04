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
						<xsl:value-of select="ns:scientificPaper/ns:title" />
					</fo:block>
					
					<fo:block space-after="18px">
						<fo:table width="100%" table-layout="fixed"
							inline-progression-dimension="100%">
							<fo:table-body>
								<fo:table-row>
									<xsl:for-each
										select="ns:scientificPaper/ns:authors/ns:author">
										<fo:table-cell text-align="center">
											<fo:block>
												<fo:block>
													<xsl:value-of select="ns:fullName" />
												</fo:block>
												<fo:block>
													<xsl:value-of select="ns:institution/ns:name" />
												</fo:block>
												<fo:block>
													<xsl:value-of select="ns:institution/ns:location/ns:City" />
													,
													<xsl:value-of select="ns:institution/ns:location/ns:State" />
													,
													<xsl:value-of select="ns:institution/ns:location/ns:Country" />
												</fo:block>
											</fo:block>
										</fo:table-cell>
									</xsl:for-each>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
					
					<fo:block space-after="14px" space-before="16px">
						<fo:block font-size="14px" space-after="5px"
							font-weight="bold">
							Abstract
						</fo:block>
						<xsl:for-each
							select="ns:scientificPaper/ns:abstract/ns:paragraph">
							<xsl:value-of select="." />
							
						</xsl:for-each>
					</fo:block>
					
					<fo:block space-after="14px" space-before="16px">
						<fo:block font-size="14px" space-after="5px"
							font-weight="bold">
							Keywords
						</fo:block>
						<xsl:for-each
							select="ns:scientificPaper//ns:keywords/ns:keyword">
							<xsl:value-of select="." />
							 
						</xsl:for-each>
					</fo:block>
					
					
					<fo:block space-after="14px" space-before="16px">
						<fo:block font-size="14px" space-after="5px"
							font-weight="bold">
							References
						</fo:block>
						<xsl:for-each
							select="ns:scientificPaper/ns:references/ns:reference">
							<xsl:value-of select="ns:refAuthors/ns:refAuthor/ns:fullName"/>
							,
							<xsl:value-of select="ns:refAuthors/ns:refAuthor/ns:institution/ns:name"/>
							,
							<xsl:value-of select="ns:refAuthors/ns:refAuthor/ns:institution/ns:location" />
							,
							<xsl:value-of select="ns:year" />
							,
							<xsl:value-of select="ns:title" />
							,
							<xsl:value-of select="ns:publisher" />
							,
							<xsl:value-of select="ns:webUrl" />
						</xsl:for-each>
					</fo:block>
				</fo:flow>
			</fo:page-sequence>

		</fo:root>

	</xsl:template>
</xsl:stylesheet>