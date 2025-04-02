package org.bloomy.project.screens.home.domain.model


data class BoldFormatterResult(
    /**
     * Indexes of content of formatter
     */
    val formattedContents: List<FormatterIndexes>,
    /**
     * Indexes of specifiers of formatter
     */
    val specifiers: List<Pair<Pair<Int, Int>, Pair<Int, Int>>>,
)

data class ItalicFormatterResult(
    /**
     * Indexes of content of formatter
     */
    val formattedContents: List<FormatterIndexes>,
    /**
     * Indexes of specifiers of formatter
     */
    val specifiers: List<Pair<Int, Int>>,
)

data class FormatterIndexes(val start: Int, val end: Int)