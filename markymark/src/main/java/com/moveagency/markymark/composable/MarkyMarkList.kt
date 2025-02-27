/*
 * Copyright © 2025 Move
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
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moveagency.markymark.composer.padding
import com.moveagency.markymark.model.NodeMetadata.Companion.Root
import com.moveagency.markymark.model.annotated.Text
import com.moveagency.markymark.model.composable.ListBlock
import com.moveagency.markymark.model.composable.TextNode
import com.moveagency.markymark.theme.LocalMarkyMarkTheme
import kotlinx.collections.immutable.persistentListOf

@Composable
fun MarkyMarkList(
    node: ListBlock,
    modifier: Modifier = Modifier,
    content: (@Composable ColumnScope.() -> Unit),
) {
    if (node.children.isEmpty()) return

    val style = LocalMarkyMarkTheme.current.styles.composable.listBlock
    Column(
        modifier = modifier
            .then(if (node.metadata.listLevel == 0) Modifier.padding(style.padding) else Modifier)
            .then(if (node.metadata.listLevel == 0) Modifier else Modifier.padding(start = style.levelIndent))
    ) {
        content()
    }
}

@Preview
@Composable
private fun PreviewListRoot() {
    val node = ListBlock(
        metadata = Root,
        children = persistentListOf(
            ListBlock.ListEntry.ListNode(
                TextNode(
                    metadata = Root,
                    text = Text(
                        metadata = Root,
                        content = "",
                    ),
                )
            )
        ),
    )

    MarkyMarkList(
        node = node,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
    ) {
        Spacer(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .background(Color.DarkGray)
        )
    }
}
