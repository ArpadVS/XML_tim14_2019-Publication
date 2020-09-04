<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:ns1="http://www.ftn.uns.ac.rs/tim14"
	xmlns:ns2="https://github.com/ArpadVS/XML_tim14_2019-Publication"
	exclude-result-prefixes="xs" version="2.0">
	<xsl:template match="/">
		<html>
			<head>
				<title> Review </title>
				<style type="text/css">
					.tab {margin-left:40px;};
				</style>
			</head>
			<body>
				<h1> Review</h1>
				<p>
					Paper-id:
					<xsl:value-of select="ns2:review/ns2:paper-id"></xsl:value-of>
					<br />
					Paper-title:
					<xsl:value-of select="ns2:review/ns2:paperTitle"></xsl:value-of>
					<br />
					Reviewer:
					<div class="tab">
						Name: <xsl:value-of select="ns2:review/ns2:reviewer/ns2:fullName"></xsl:value-of><br/>
						Institution: <xsl:value-of select="ns2:review/ns2:reviewer/ns2:institution"></xsl:value-of><br/>
						Email: <xsl:value-of select="ns2:review/ns2:reviewer/ns2:email"></xsl:value-of><br/>
					</div>
					<br />
					Comments:
					<br></br>
					<div class="tab">
						<xsl:for-each
							select="ns2:review/ns2:comments/ns2:comment">
							Element-id:
							<xsl:value-of select="ns2:element-id"></xsl:value-of>
							<br></br>
							Comment:
							<xsl:value-of select="ns2:comment"></xsl:value-of>
							<br></br><br></br>
						</xsl:for-each>
					</div>
					Summary:
					<xsl:value-of select="ns2:review/ns2:summary"></xsl:value-of>
					<br />
					Grade:
					<xsl:value-of select="ns2:review/ns2:grade"></xsl:value-of>
					<br />

				</p>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>