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
        	 	<title> Scientific Paper </title>
        	 	<style type="text/css">
					.tab {margin-left:40px;
					margin-top:0px;};
				</style>
        	</head>
        	<body>
        		<h1>Scientific Paper</h1>
        		<p>
        			 <b>Title: </b> <xsl:value-of select="ns2:scientificPaper/ns2:title"></xsl:value-of><br/>
        			 <hr></hr>
        			 <b>Authors: </b> 
        			 <p class="tab"><xsl:for-each select="ns2:scientificPaper/ns2:authors/ns2:author"> <br/>
        			 	<xsl:value-of select="ns2:fullName"></xsl:value-of><br/>
        			 	<xsl:value-of select="ns2:institution"></xsl:value-of><br/>
        			 	<xsl:value-of select="ns2:email"></xsl:value-of><br/>
        			 </xsl:for-each> </p><br/>
        			 <hr></hr>
        			 <b>Abstract</b> <xsl:value-of select="ns2:scientificPaper/ns2:abstract"></xsl:value-of><br/> 
        			 <hr></hr>
        			 <b>Keywords</b>
        			  <xsl:value-of select="ns2:scientificPaper/ns2:keywords"></xsl:value-of><br/>
        			<hr/>
        			<b>Content</b>
        			 <p class="tab"><xsl:for-each select="ns2:scientificPaper/ns2:content/ns2:chapter"> <br/>
        			 	Paragraph:
        			 	Title: <xsl:value-of select="ns2:title"></xsl:value-of>
						List: <xsl:value-of select="ns2:paragraph/ns2:list"></xsl:value-of>  
						Quote: <xsl:value-of select="ns2:paragraph/ns2:quote"></xsl:value-of>
						Ref: <xsl:value-of select="ns2:paragraph/ns2:ref"></xsl:value-of>
						Formule: <xsl:value-of select="ns2:paragraph/ns2:formule"></xsl:value-of>
						 Picture: <xsl:value-of select="ns2:picture"></xsl:value-of><br/>
						 Table: <xsl:value-of select="ns2:table"></xsl:value-of> <br/>
						 
						 Figure:
						  <xsl:for-each select="ns2:figure"> <br></br>
						 	Title: <xsl:value-of select="ns2:title"></xsl:value-of>
						 	Image: <xsl:value-of select="ns2:image"></xsl:value-of>
						 </xsl:for-each> <br/>
        			 </xsl:for-each></p> <br/>
        			 <hr></hr>
        			 <b>References</b> 
        			 <p class="tab">
        			 <xsl:for-each select="ns2:scientificPaper/ns2:references/ns2:reference"><br></br>
        			 	Authors: <xsl:value-of select="ns2:refAuthors"></xsl:value-of><br/>
        			 	Year: <xsl:value-of select="ns2:year"></xsl:value-of><br/>
        			 	Title: <xsl:value-of select="ns2:title"></xsl:value-of><br/>
        			 	Publisher: <xsl:value-of select="ns2:publisher"></xsl:value-of>
        			 	<br/>
        			 	WebURL: <xsl:value-of select="ns2:webUrl"></xsl:value-of><br/>
        			 </xsl:for-each>
        			  </p>
        			 <hr></hr>
        			 <b> Statuses: </b> <br/>
        			 <p class="tab">
        			 Received: <xsl:value-of select="ns2:scientificPaper/@ns2:recieved"></xsl:value-of> <br/>
        			  Revised: <xsl:value-of select="ns2:scientificPaper/ns2:revised"></xsl:value-of><br/>
        			   Accepted: <xsl:value-of select="ns2:scientificPaper/ns2:accepted"></xsl:value-of><br/>
        			    Id: <xsl:value-of select="ns2:scientificPaper/ns2:id"></xsl:value-of><br/>
        			     Language: <xsl:value-of select="ns2:scientificPaper/ns2:language"></xsl:value-of><br/>
        			      Status: <xsl:value-of select="ns2:scientificPaper/ns2:status"></xsl:value-of><br/>
        			      </p>
        	
        		</p>
        	
        	</body>
        </html>
        
    </xsl:template>
</xsl:stylesheet>