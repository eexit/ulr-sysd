<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:doc="http://www.doc.org"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <xsl:output method="xml" indent="no" version="1.0" encoding="UTF-8"/>
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
    <xsl:template match="doc:titre">
        <fo:block font-size="18pt" font-weight="bold" text-align="center" margin-bottom="2cm">
            <xsl:apply-templates/>
        </fo:block>
    </xsl:template>
    <xsl:template match="doc:resume">
        <fo:block font-size="11pt"><fo:inline font-weight="bold">Résumé : </fo:inline><xsl:apply-templates/></fo:block>
    </xsl:template>
    <xsl:template match="doc:motcles">
        <fo:block font-size="11px"><fo:inline font-weight="bold">Mots-clés : </fo:inline><xsl:apply-templates/></fo:block>
    </xsl:template>
    <xsl:template match="doc:motcle">
        <xsl:if test="position() &lt; last() - 1">
            <fo:inline font-size="11pt"><xsl:apply-templates/>, </fo:inline>
        </xsl:if>
        <xsl:if test="position() = last() - 1">
            <fo:inline font-size="11pt"><xsl:apply-templates/>.</fo:inline>
        </xsl:if>
    </xsl:template>
    <xsl:template match="doc:section">
        <fo:block-container margin-top="1cm" margin-bottom="1cm">
            <fo:block font-size="14pt" font-weight="bold" text-transform="capitalize" margin-bottom="0.3cm">
                <xsl:number format="1" level="single" from="doc:section"/>
                <fo:inline padding-left="0.5cm"><xsl:apply-templates select="@titre"/></fo:inline>
            </fo:block>
            <fo:block font-size="12pt" margin-left="0.5cm"><xsl:apply-templates/></fo:block>
        </fo:block-container>
    </xsl:template>
</xsl:stylesheet>