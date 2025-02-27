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
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.tooling.preview.Preview
import com.moveagency.markymark.composer.padding
import com.moveagency.markymark.model.NodeMetadata.Companion.Root
import com.moveagency.markymark.model.composable.CodeBlock
import com.moveagency.markymark.theme.LocalMarkyMarkTheme
import dev.snipme.highlights.Highlights
import dev.snipme.highlights.model.SyntaxLanguage
import dev.snipme.highlights.model.SyntaxThemes

@Composable
fun MarkyMarkCode(
    node: CodeBlock,
    modifier: Modifier = Modifier,
) {
    val style = LocalMarkyMarkTheme.current.styles.composable.codeBlock
    val colors = LocalMarkyMarkColors.current.codeBlock
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .then(modifier)
    ) {
        SelectionContainer {
            val textModifier = Modifier
                .padding(style.outerPadding)
                .clip(style.shape)
                .then(if (colors.background.isSpecified) Modifier.background(colors.background) else Modifier)
                .padding(style.innerPadding)

            val language = remember(node.language) { node.language?.let(SyntaxLanguage::getByName) }

            if (language == null) {
                Text(
                    modifier = textModifier,
                    color = colors.text,
                    style = style.textStyle,
                    text = node.content,
                )
            } else {
                val isDarkMode = isSystemInDarkTheme()
                val highlights = remember(isDarkMode, language) {
                    Highlights.Builder()
                        .code(node.content)
                        .language(language)
                        .theme(SyntaxThemes.darcula(isDarkMode))
                        .build()
                }

                CodeText(
                    highlights = highlights,
                    modifier = textModifier,
                    color = colors.text,
                    style = style.textStyle,
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCodeBlock() {
    val node = CodeBlock(
        metadata = Root,
        content = """
        val someText = "I am text"
            
        fun something() {
            println(someText.clean())   
        }
        
        fun String.clean() = trim()
        """.trimIndent(),
        language = "kotlin",
    )

    MarkyMarkCode(
        node = node,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
    )
}

@Preview
@Composable
private fun PreviewCodeBlockNoLanguage() {
    val node = CodeBlock(
        metadata = Root,
        content = """
        val someText = "I am text"
            
        fun something() {
            println(someText.clean())   
        }
        
        fun String.clean() = trim()
        """.trimIndent(),
        language = null,
    )

    MarkyMarkCode(
        node = node,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
    )
}
