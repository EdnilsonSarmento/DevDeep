<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text" omit-xml-declaration="yes" indent="no"/>
    <xsl:template match="/">
        flag,name,capital,region,subregion,population,area,timezones
        <xsl:for-each select="//element">
            <xsl:value-of select="concat(flag,',',name,',',capital,',',region,',',subregion,',',population,',',area,',',timezones,'&#xA;')"/>
        </xsl:for-each>
    </xsl:template>

</xsl:stylesheet>