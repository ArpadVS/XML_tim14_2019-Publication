<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:ns1="http://www.ftn.uns.ac.rs/tim14"
	xmlns:ns="https://github.com/ArpadVS/XML_tim14_2019-Publication"
	exclude-result-prefixes="xs" version="2.0">
	<xsl:template match="/">
		<html>
			<head>
				<title> Scientific Paper </title>
				<style type="text/css">
					.tab {
					margin-left:40px;
					margin-top:0px;
					};
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

					.align-center
					{
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
			</head>
			<body>
				<div class="center-content">
					<h1 class="align-center">
						<xsl:value-of select="ns:scientificPaper/ns:title"></xsl:value-of>
					</h1>
					<div style="margin-bottom:50px;">
						<xsl:for-each
							select="ns:scientificPaper/ns:authors/ns:author">
							<p class="align-center">
								<xsl:value-of select="ns:fullName"></xsl:value-of>
								&#160;
							</p>
							<p class="align-center"
								style="font-style:italic;font-size: 12px;margin-bottom:2px;">
								<xsl:value-of select="ns:institution/ns:name"></xsl:value-of>
								,
								<xsl:value-of
									select="ns:institution/ns:location/ns:City"></xsl:value-of>
								<xsl:value-of
									select="ns:institution/ns:location/ns:State"></xsl:value-of>
								<xsl:value-of
									select="ns:institution/ns:location/ns:Country"></xsl:value-of>
							</p>
						</xsl:for-each>
					</div>

					<div style="margin-bottom:30px;">
						<p class="align-justify" style="margin-bottom:2px;">
							<span class="bold">Abstract</span>
							-
							<xsl:for-each
								select="ns:scientificPaper/ns:abstract">
								<xsl:value-of select="ns:paragraph">
								</xsl:value-of>
							</xsl:for-each>
						</p>
					</div>
					<div style="margin-bottom:30px;">
						<p class="align-justify" style="margin-bottom:2px;">
							<span class="bold">Keywords</span>
							-
							<xsl:value-of
								select="ns:scientificPaper/ns:keywords"></xsl:value-of>
						</p>
					</div>

					<xsl:for-each
						select="ns:scientificPaper/ns:content/ns:chapter">
						<p class="bold"
							style="font-size:18px;margin-bottom:10px;margin-top:20px;">
							<xsl:value-of select="ns:title"></xsl:value-of>
						</p>
						<div class="tab">
							<p class="bold"
								style="font-size:18px;margin-bottom:10px;margin-top:20px;">
								<xsl:value-of select="ns:figure"></xsl:value-of>
							</p>
							<p class="align-justify"
								style="text-index:20px;font-size:16px;margin-bottom:5px">
								<b>Paragraph details:</b> <br/>
								List:
								<xsl:value-of select="ns:paragraph/ns:list"></xsl:value-of>
								<br></br>
								Quote:
								<xsl:value-of select="ns:paragraph/ns:quote"></xsl:value-of>
								<br></br>
								Ref:
								<xsl:value-of select="ns:paragraph/ns:ref"></xsl:value-of>
								<br></br>
								Internal-ref:
								<xsl:value-of select="ns:paragraph/ns:internal-ref"></xsl:value-of>
								<br></br>
							</p>
						</div>
					</xsl:for-each>
					<div>
                        <p class="bold" style="font-size:14px;margin-bottom:5px;margin-top:30px;">References</p>
                        <xsl:for-each select="ns:scientificPaper/ns:references/ns:reference">
                            <a href="{@href}" style="margin-bottom:5px;font-size:12px;">
                                <xsl:for-each select="ns:refAuthors/ns:refAuthor">
                                    <xsl:value-of select="ns:fullName"></xsl:value-of>&#160;
                                    <xsl:value-of select="ns:institution/ns:name"></xsl:value-of> 
                                    <xsl:value-of select="ns:institution/ns:location"></xsl:value-of>
                                    <xsl:value-of select="ns:email"></xsl:value-of>,  
                                </xsl:for-each>
                                <xsl:value-of select="ns:year"></xsl:value-of>&#160;
                                <xsl:value-of select="ns:title"></xsl:value-of>&#160;
                                <xsl:value-of select="ns:publisher"></xsl:value-of> &#160;
                                <xsl:value-of select="ns:webUrl"></xsl:value-of>
                            </a>
                        </xsl:for-each>
                    </div>
				</div>
			</body>
		</html>
	</xsl:template>
	<xsl:template match="ns:scientificPaper/ns:content/ns:chapter/ns:paragraph/ns:bold">
        <span class="bold">
            <xsl:apply-templates select="node()"/>
        </span>  
    </xsl:template>
    
    <xsl:template match="ns:scientificPaper/ns:content/ns:chapter/ns:paragraph/ns:italic">
        <span class="italic">
            <xsl:apply-templates select="node()"/>
        </span> 
    </xsl:template>
</xsl:stylesheet>