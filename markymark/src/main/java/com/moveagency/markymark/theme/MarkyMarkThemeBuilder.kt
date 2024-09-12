/*
 * Copyright © 2024 Move
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the “Software”), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of
 * the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.moveagency.markymark.theme

import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import com.moveagency.markymark.theme.code.CodeBlockStyle
import com.moveagency.markymark.theme.heading.HeadingLevelStyle
import com.moveagency.markymark.theme.heading.HeadingsStyle
import com.moveagency.markymark.theme.image.ImageCaptionStyle
import com.moveagency.markymark.theme.image.ImageStyle
import com.moveagency.markymark.theme.list.ListBlockStyle
import com.moveagency.markymark.theme.paragraph.ParagraphStyle
import com.moveagency.markymark.theme.quote.BlockQuoteStyle
import com.moveagency.markymark.theme.rule.RuleStyle
import com.moveagency.markymark.theme.table.TableBlockStyle

fun markyMarkTheme(block: MarkyMarkThemeBuilder.() -> Unit): MarkyMarkTheme {
    val builder = MarkyMarkThemeBuilder()
    block(builder)
    return builder.build()
}

@MarkyMarkThemeBuilderMarker
class MarkyMarkThemeBuilder {

    private var root = RootBuilder()
    private var styles = MarkyMarkStylesBuilder()
    private var colors = MarkyMarkColors.Builder()

    fun include(builder: MarkyMarkThemeBuilder) {
        root = builder.root
        styles = builder.styles
        colors = builder.colors
    }

    fun include(theme: MarkyMarkTheme) {
        root.include(theme.root)
        styles.include(theme.styles)
        colors.include(theme.colors)
    }

    fun root(block: RootBuilder.() -> Unit) = block(root)

    fun styles(block: MarkyMarkStylesBuilder.() -> Unit) = block(styles)

    fun colors(block: MarkyMarkColors.Builder.() -> Unit) = block(colors)

    internal fun build() = MarkyMarkTheme(
        root = root.build(),
        styles = styles.build(),
        colors = colors.build(),
    )
}

@MarkyMarkThemeBuilderMarker
class RootBuilder {

    private var padding: Padding.Builder = Padding.Builder()

    fun include(builder: RootBuilder) {
        padding = builder.padding
    }

    fun include(root: RootStyle) {
        padding.include(root.screenPadding)
    }

    fun padding(block: Padding.Builder.() -> Unit) = block(padding)

    internal fun build() = RootStyle(
        screenPadding = padding.build(),
    )
}

@MarkyMarkThemeBuilderMarker
class MarkyMarkStylesBuilder {

    private var composable = ComposableStylesBuilder()
    private var annotated = AnnotatedStyles.Builder()

    fun include(builder: MarkyMarkStylesBuilder) {
        composable = builder.composable
        annotated = builder.annotated
    }

    fun include(styles: MarkyMarkStyles) {
        composable.include(styles.composable)
        annotated.include(styles.annotated)
    }

    fun composable(block: ComposableStylesBuilder.() -> Unit) = block(composable)

    fun annotated(block: AnnotatedStyles.Builder.() -> Unit) = block(annotated)

    internal fun build() = MarkyMarkStyles(
        composable = composable.build(),
        annotated = annotated.build(),
    )
}

@MarkyMarkThemeBuilderMarker
class ComposableStylesBuilder {

    private var headings = HeadingsStyleBuilder()
    private var image = ImageStyleBuilder()
    private var paragraph = ParagraphStyleBuilder()
    private var rule = RuleStyle.Builder()
    private var codeBlock = CodeBlockStyle.Builder()
    private var blockQuote = BlockQuoteStyle.Builder()
    private var tableBlock = TableBlockStyle.Builder()
    private var listBlock = ListBlockStyle.Builder()

    fun include(builder: ComposableStylesBuilder) {
        headings = builder.headings
        image = builder.image
        paragraph = builder.paragraph
        rule = builder.rule
        codeBlock = builder.codeBlock
        blockQuote = builder.blockQuote
        tableBlock = builder.tableBlock
        listBlock = builder.listBlock
    }

    fun include(styles: ComposableStyles) {
        headings.include(styles.headings)
        image.include(styles.image)
        paragraph.include(styles.paragraph)
        rule.include(styles.rule)
        codeBlock.include(styles.codeBlock)
        blockQuote.include(styles.blockQuote)
        tableBlock.include(styles.tableBlock)
        listBlock.include(styles.listBlock)
    }

    fun headings(block: HeadingsStyleBuilder.() -> Unit) = block(headings)

    fun image(block: ImageStyleBuilder.() -> Unit) = block(image)

    fun paragraph(block: ParagraphStyleBuilder.() -> Unit) = block(paragraph)

    fun rule(block: RuleStyle.Builder.() -> Unit) = block(rule)

    fun codeBlock(block: CodeBlockStyle.Builder.() -> Unit) = block(codeBlock)

    fun blockQuote(block: BlockQuoteStyle.Builder.() -> Unit) = block(blockQuote)

    fun tableBlock(block: TableBlockStyle.Builder.() -> Unit) = block(tableBlock)

    fun listBlock(block: ListBlockStyle.Builder.() -> Unit) = block(listBlock)

    internal fun build() = ComposableStyles(
        headings = headings.build(),
        image = image.build(),
        paragraph = paragraph.build(),
        rule = rule.build(),
        codeBlock = codeBlock.build(),
        blockQuote = blockQuote.build(),
        tableBlock = tableBlock.build(),
        listBlock = listBlock.build(),
    )
}

@MarkyMarkThemeBuilderMarker
class HeadingsStyleBuilder {

    private var h1 = HeadingLevelStyleBuilder()
    private var h2 = HeadingLevelStyleBuilder()
    private var h3 = HeadingLevelStyleBuilder()
    private var h4 = HeadingLevelStyleBuilder()
    private var h5 = HeadingLevelStyleBuilder()
    private var h6 = HeadingLevelStyleBuilder()

    fun include(builder: HeadingsStyleBuilder) {
        h1 = builder.h1
        h2 = builder.h2
        h3 = builder.h3
        h4 = builder.h4
        h5 = builder.h5
    }

    fun include(styles: HeadingsStyle) {
        h1.include(styles.h1)
        h2.include(styles.h2)
        h3.include(styles.h3)
        h4.include(styles.h4)
        h5.include(styles.h5)
        h6.include(styles.h6)
    }

    fun h1(block: HeadingLevelStyleBuilder.() -> Unit) = block(h1)
    fun h2(block: HeadingLevelStyleBuilder.() -> Unit) = block(h2)
    fun h3(block: HeadingLevelStyleBuilder.() -> Unit) = block(h3)
    fun h4(block: HeadingLevelStyleBuilder.() -> Unit) = block(h4)
    fun h5(block: HeadingLevelStyleBuilder.() -> Unit) = block(h5)
    fun h6(block: HeadingLevelStyleBuilder.() -> Unit) = block(h6)

    internal fun build() = HeadingsStyle(
        h1 = h1.build(),
        h2 = h2.build(),
        h3 = h3.build(),
        h4 = h4.build(),
        h5 = h5.build(),
        h6 = h6.build(),
    )
}

@MarkyMarkThemeBuilderMarker
class HeadingLevelStyleBuilder {

    private var padding = Padding.Builder()
    var textStyle = TextStyle()

    fun include(builder: HeadingLevelStyleBuilder) {
        padding = builder.padding
        textStyle = builder.textStyle
    }

    fun include(style: HeadingLevelStyle) {
        padding.include(style.padding)
        textStyle = style.textStyle
    }

    fun padding(block: Padding.Builder.() -> Unit) = block(padding)

    internal fun build() = HeadingLevelStyle(
        padding = padding.build(),
        textStyle = textStyle,
    )
}

@MarkyMarkThemeBuilderMarker
class ImageStyleBuilder {

    var shape = RectangleShape

    private var padding = Padding.Builder()
    private var caption = ImageCaptionStyleBuilder()

    fun include(builder: ImageStyleBuilder) {
        shape = builder.shape
        padding = builder.padding
        caption = builder.caption
    }

    fun include(style: ImageStyle) {
        shape = style.shape
        padding.include(style.padding)
        caption.include(style.caption)
    }

    fun padding(block: Padding.Builder.() -> Unit) = block(padding)

    fun caption(block: ImageCaptionStyleBuilder.() -> Unit) = block(caption)

    internal fun build() = ImageStyle(
        shape = shape,
        padding = padding.build(),
        caption = caption.build(),
    )
}

@MarkyMarkThemeBuilderMarker
class ImageCaptionStyleBuilder {

    private var padding = Padding.Builder()
    var textStyle = TextStyle()
    var alignment = Start

    fun include(builder: ImageCaptionStyleBuilder) {
        padding = builder.padding
        textStyle = builder.textStyle
        alignment = builder.alignment
    }

    fun include(style: ImageCaptionStyle) {
        padding.include(style.padding)
        textStyle = style.textStyle
        alignment = style.alignment
    }

    fun padding(block: Padding.Builder.() -> Unit) = block(padding)

    internal fun build() = ImageCaptionStyle(
        padding = padding.build(),
        textStyle = textStyle,
        alignment = alignment,
    )
}

@MarkyMarkThemeBuilderMarker
class ParagraphStyleBuilder {

    private var padding = Padding.Builder()
    var textStyle = TextStyle()

    fun include(builder: ParagraphStyleBuilder) {
        padding = builder.padding
        textStyle = builder.textStyle
    }

    fun include(style: ParagraphStyle) {
        padding.include(style.padding)
        textStyle = style.textStyle
    }

    fun padding(block: Padding.Builder.() -> Unit) = block(padding)

    internal fun build() = ParagraphStyle(
        padding = padding.build(),
        textStyle = textStyle,
    )
}
