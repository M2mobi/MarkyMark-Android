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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.moveagency.markymark.model.NodeMetadata.Companion.Root
import com.moveagency.markymark.model.annotated.AnnotatedStableNode
import com.moveagency.markymark.model.annotated.Text
import com.moveagency.markymark.theme.LocalMarkyMarkTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

internal const val TAG_LINK = "TAG_LINK"

@Composable
fun MarkyMarkText(
    nodes: ImmutableList<AnnotatedStableNode>,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalMarkyMarkTheme.current.styles.composable.paragraph.textStyle,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    val textColor = LocalContentColor.current
    val text = annotate(nodes = nodes)
    val uriHandler = LocalUriHandler.current
    SelectionContainer(modifier = modifier) {
        ClickableText(
            text = text,
            style = style.merge(color = textColor),
            onTextLayout = onTextLayout,
            onClick = { onTextClicked(offset = it, text = text, uriHandler = uriHandler) },
        )
    }
}

private fun onTextClicked(offset: Int, text: AnnotatedString, uriHandler: UriHandler) {
    text.getStringAnnotations(TAG_LINK, offset, offset)
        .firstOrNull()
        ?.let { uriHandler.openUri(it.item) }
}

@Preview
@Composable
private fun PreviewTextNode() {
    MarkyMarkText(
        nodes = persistentListOf(
            Text(
                metadata = Root,
                content = "Indeed, the ratio of time spent reading versus writing is well over 10 to 1. We are " +
                    "constantly reading old code as part of the effort to write new code. ...[Therefore,] making it " +
                    "easy to read makes it easier to write.",
            ),
        ),
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth(),
    )
}
