<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:doc="http://www.doc.org"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <xsl:output method="xml" indent="no" version="1.0" encoding="UTF-8"/>
    <!-- Root XML matching -->
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="A4" page-height="297mm" page-width="210mm" margin-top="1cm" margin-bottom="1cm" margin-left="1cm" margin-right="1cm">
                    <fo:region-body/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="A4">
                <fo:flow flow-name="xsl-region-body" font-family="serif">
                    <xsl:apply-templates/>
                    <xsl:for-each select="//doc:section">
                        <xsl:apply-templates/>
                    </xsl:for-each>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
    <!-- XML doc:titre element matching -->
    <xsl:template match="doc:titre">
        <fo:block font-size="18pt" font-weight="bold" text-align="center" margin-bottom="2cm">
            <xsl:apply-templates/>
        </fo:block>
    </xsl:template>
    <!-- XML doc:resume element matching -->
    <xsl:template match="doc:resume">
        <fo:block font-size="11pt">
            <fo:inline font-weight="bold">Résumé : </fo:inline>
            <xsl:apply-templates/>
        </fo:block>
    </xsl:template>
    <!-- XML doc:motcles element matching -->
    <xsl:template match="doc:motcles">
        <fo:block font-size="11px">
            <fo:inline font-weight="bold">Mots-clés : </fo:inline>
            <xsl:apply-templates/>
        </fo:block>
    </xsl:template>
    <!-- XML doc:motcle elements matching -->
    <xsl:template match="doc:motcle">
        <xsl:if test="position() &lt; last() - 1">
            <fo:inline font-size="11pt">
                <xsl:apply-templates/>, 
            </fo:inline>
        </xsl:if>
        <xsl:if test="position() = last() - 1">
            <fo:inline font-size="11pt">
                <xsl:apply-templates/>.
            </fo:inline>
        </xsl:if>
    </xsl:template>
    <!-- XML doc:section elements matching -->
    <xsl:template match="doc:section">
        <fo:block-container margin-top="1cm" margin-bottom="1cm">
            <fo:block font-size="14pt" font-weight="bold" margin-bottom="0.3cm">
                <xsl:number format="1" level="single" from="doc:section"/>
                <fo:inline padding-left="0.5cm">
                    <xsl:call-template name="ucfirst">
                        <xsl:with-param name="input" select="@titre"/>
                    </xsl:call-template>
                </fo:inline>
            </fo:block>
            <fo:block font-size="12pt" margin-left="0.5cm">
                <xsl:apply-templates/>
            </fo:block>
        </fo:block-container>
    </xsl:template>
    <!-- XSL first letter uppercase function -->
    <xsl:template name="ucfirst">
        <xsl:param name="input"/>
        <xsl:param name="strlen" select="string-length($input)"/>
        <xsl:variable name="firstletter" select="substring($input, 1, 1)"/>
        <xsl:variable name="rest" select="substring($input, 2, $strlen)"/>
        <xsl:variable name="lower" select="'abcdefghijklmnopqrstuvwxyz'"/>
        <xsl:variable name="upper" select="'ABCDEFGHIJKLMNOPQRSTUVWXYZ'"/>
        <xsl:variable name="translate" select="translate($firstletter, $lower, $upper)"/>
        <xsl:value-of select="concat($translate, $rest)"/>
    </xsl:template>
</xsl:stylesheet>