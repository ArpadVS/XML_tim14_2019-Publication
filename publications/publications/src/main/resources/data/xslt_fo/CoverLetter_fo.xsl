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
					<fo:region-end extent="2cm" />
				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="sciPage">
				<fo:flow flow-name="xsl-region-body" font-size="12px"
					font-family="Times" text-align="justify">

					<fo:block text-align="center" font-size="22px"
						space-after="16px">
						Cover Letter
					</fo:block>

					<fo:block space-after="50px">
						<fo:table width="100%" table-layout="fixed"
							inline-progression-dimension="100%">
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell text-align="center">
										<fo:block>
											<fo:block>
												<xsl:value-of
													select="ns:Cover_letter/ns:sender_contact/ns:Name" />
											</fo:block>
											<fo:block>
												<xsl:value-of
													select="ns:Cover_letter/ns:sender_contact/ns:Phone_number" />
											</fo:block>
											<fo:block>
												<xsl:value-of
													select="ns:Cover_letter/ns:sender_contact/ns:Location" />
											</fo:block>
											<fo:block>
												<xsl:value-of
													select="ns:Cover_letter/ns:sender_contact/ns:Email" />
											</fo:block>
										</fo:block>
									</fo:table-cell>

									<fo:table-cell text-align="center">
										<fo:block>
											<fo:block>
												<xsl:value-of
													select="ns:Cover_letter/ns:employer_contact/ns:Name" />
											</fo:block>
											<fo:block>
												<xsl:value-of
													select="ns:Cover_letter/ns:employer_contact/ns:Phone_number" />
											</fo:block>
											<fo:block>
												<xsl:value-of
													select="ns:Cover_letter/ns:employer_contact/ns:Location" />
											</fo:block>
											<fo:block>
												<xsl:value-of
													select="ns:Cover_letter/ns:employer_contact/ns:Email" />
											</fo:block>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>

					<fo:block space-after="40px">
						<xsl:for-each select="ns:Cover_letter/ns:body">
							<xsl:value-of select="ns:Paragraphs"> </xsl:value-of>
						</xsl:for-each>
					</fo:block>
					
					<fo:block space-after="3px">
						<xsl:value-of select="ns:Cover_letter/ns:body/ns:Thanksgiving"></xsl:value-of>
					</fo:block>
					<fo:block>
						<xsl:value-of select="ns:Cover_letter/ns:body/ns:Closure/ns:Compliment"></xsl:value-of>
						,
					</fo:block>
					<fo:block>
						<xsl:value-of select="ns:Cover_letter/ns:body/ns:Closure/ns:Signature"></xsl:value-of>
					</fo:block>


				</fo:flow>

			</fo:page-sequence>
		</fo:root>
	</xsl:template>
	
</xsl:stylesheet>