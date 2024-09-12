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

package com.moveagency.markymark.theme

import androidx.compose.runtime.Immutable
import com.moveagency.markymark.theme.code.CodeBlockStyle
import com.moveagency.markymark.theme.heading.HeadingsStyle
import com.moveagency.markymark.theme.image.ImageStyle
import com.moveagency.markymark.theme.list.ListBlockStyle
import com.moveagency.markymark.theme.paragraph.ParagraphStyle
import com.moveagency.markymark.theme.quote.BlockQuoteStyle
import com.moveagency.markymark.theme.rule.RuleStyle
import com.moveagency.markymark.theme.table.TableBlockStyle

/**
 * Theming attributes used when rendering [ComposableStableNode]s with [DefaultMarkyMarkComposer.createNode]. The
 * pairings of style to [ComposableStableNode] to default composer function are as follows:
 *
 * - [headings] -> [Headline] -> [MarkyMarkHeadline]
 * - [image] -> [Image] -> [MarkyMarkImage]
 * - [paragraph] -> [Paragraph] -> [MarkyMarkParagraph]
 * - [rule] -> [Rule] -> [MarkyMarkRule]
 * - [codeBlock] -> [CodeBlock] -> [MarkyMarkCode]
 * - [blockQuote] -> [BlockQuote] -> [MarkyMarkQuote]
 * - [tableBlock] -> [TableBlock] -> [MarkyMarkTable]
 * - [listBlock] -> [ListBlock] -> [MarkyMarkList]
 * - [textNode] -> [AnnotatedStableNode] -> [MarkyMarkText]
 */
@Immutable
data class ComposableStyles(
    val headings: HeadingsStyle,
    val image: ImageStyle,
    val paragraph: ParagraphStyle,
    val rule: RuleStyle,
    val codeBlock: CodeBlockStyle,
    val blockQuote: BlockQuoteStyle,
    val tableBlock: TableBlockStyle,
    val listBlock: ListBlockStyle,
)
