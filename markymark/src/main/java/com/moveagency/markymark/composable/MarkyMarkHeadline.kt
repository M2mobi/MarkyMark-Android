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

package com.moveagency.markymark.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.moveagency.markymark.composer.padding
import com.moveagency.markymark.model.NodeMetadata.Companion.Root
import com.moveagency.markymark.model.annotated.Bold
import com.moveagency.markymark.model.annotated.Text
import com.moveagency.markymark.model.composable.Headline
import com.moveagency.markymark.model.composable.Headline.Level.*
import com.moveagency.markymark.theme.LocalMarkyMarkTheme
import kotlinx.collections.immutable.persistentListOf

@Composable
fun MarkyMarkHeadline(
    node: Headline,
    modifier: Modifier = Modifier,
) {
    val headingsStyles = LocalMarkyMarkTheme.current.styles.composable.headings
    val style = when (node.headingLevel) {
        Heading1 -> headingsStyles.h1
        Heading2 -> headingsStyles.h2
        Heading3 -> headingsStyles.h3
        Heading4 -> headingsStyles.h4
        Heading5 -> headingsStyles.h5
        Heading6 -> headingsStyles.h6
    }

    val colors = LocalMarkyMarkColors.current.headings
    val color = when (node.headingLevel) {
        Heading1 -> colors.h1
        Heading2 -> colors.h2
        Heading3 -> colors.h3
        Heading4 -> colors.h4
        Heading5 -> colors.h5
        Heading6 -> colors.h6
    }

    CompositionLocalProvider(LocalContentColor provides color) {
        MarkyMarkText(
            modifier = modifier.padding(style.padding),
            nodes = node.children,
            style = style.textStyle,
        )
    }
}

@Preview
@Composable
private fun PreviewHeadlineOne() = MarkyMarkHeadline(
    node = createHeadingNode(Heading1),
    modifier = Modifier
        .fillMaxWidth()
        .background(Color.White),
)

@Preview
@Composable
private fun PreviewHeadlineTwo() = MarkyMarkHeadline(
    node = createHeadingNode(Heading2),
    modifier = Modifier
        .fillMaxWidth()
        .background(Color.White),
)

@Preview
@Composable
private fun PreviewHeadlineThree() = MarkyMarkHeadline(
    node = createHeadingNode(Heading3),
    modifier = Modifier
        .fillMaxWidth()
        .background(Color.White),
)

@Preview
@Composable
private fun PreviewHeadlineFour() = MarkyMarkHeadline(
    node = createHeadingNode(Heading4),
    modifier = Modifier
        .fillMaxWidth()
        .background(Color.White),
)

@Preview
@Composable
private fun PreviewHeadlineFive() = MarkyMarkHeadline(
    node = createHeadingNode(Heading5),
    modifier = Modifier
        .fillMaxWidth()
        .background(Color.White),
)

@Preview
@Composable
private fun PreviewHeadlineSix() = MarkyMarkHeadline(
    node = createHeadingNode(Heading6),
    modifier = Modifier
        .fillMaxWidth()
        .background(Color.White),
)

private fun createHeadingNode(level: Headline.Level) = Headline(
    metadata = Root,
    children = persistentListOf(
        Text(
            metadata = Root,
            content = "Heading text "
        ),
        Bold(
            metadata = Root,
            children = persistentListOf(
                Text(
                    metadata = Root,
                    content = "bold part"
                )
            )
        ),
        Text(
            metadata = Root,
            content = " normal part"
        ),
    ),
    headingLevel = level,
)
