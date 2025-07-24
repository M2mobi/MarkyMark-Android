/*
 * Copyright © 2025 Framna
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

package com.moveagency.markymark.model.composable

import androidx.compose.runtime.Immutable
import com.moveagency.markymark.model.NodeMetadata

/**
 * Represents a Markdown code block. Mapped from [CodeBlock][com.vladsch.flexmark.ast.CodeBlock].
 *
 * Code blocks are used to display code snippets or preformatted text with preserved whitespace and
 * monospace font. They are visually distinct from regular text, typically with a different background
 * color or border.
 *
 * Markdown supports two types of code blocks:
 *
 * __Indented Code Blocks:__
 *
 * Created by indenting each line with at least 4 spaces or 1 tab:
 *
 * ```markdown
 *     ./gradlew clean
 *     ./gradlew assembleDebug
 *     ./gradlew publishToMavenLocal
 * ```
 *
 * Indented code blocks:
 * - Must be preceded by a blank line (unless following a list)
 * - Cannot specify a language for syntax highlighting
 * - Preserve all whitespace and indentation within the block
 *
 * For details see the [Markdown guide](https://www.markdownguide.org/basic-syntax#code-blocks).
 *
 * __Fenced Code Blocks:__
 *
 * Created by surrounding the code with triple backticks (```) or triple tildes (~~~):
 *
 * ````markdown
 * ```kotlin
 * fun doSomething() {
 *     println("stuff and things")
 * }
 * ```
 * ````
 *
 * Fenced code blocks:
 * - Do not require indentation
 * - Can specify a language identifier for syntax highlighting
 * - Can appear immediately after other elements without blank lines
 * - Can contain backticks if using more backticks for the fence or using tildes
 *
 * For details see the [Markdown guide](https://www.markdownguide.org/extended-syntax/#fenced-code-blocks).
 *
 * __Language Specification:__
 *
 * The language identifier is optional and appears after the opening fence:
 *
 * ```markdown
 * ```java
 * ```python
 * ```json
 * ```css
 * ```
 *
 * Common language identifiers include: `java`, `kotlin`, `python`, `javascript`, `html`, `css`,
 * `json`, `xml`, `yaml`, `bash`, `sql`, and many others.
 *
 * __Important Notes:__
 *
 * - Code blocks are different from inline code, which is created with single backticks (`code`).
 * - While the language will be parsed and stored in the [language] property, this library does not
 *   provide syntax highlighting functionality.
 * - All whitespace and line breaks within the code block are preserved exactly as written.
 *
 * @property metadata Contains information about this code block node, including its position in the
 *   document hierarchy and any additional metadata.
 * @property content The raw text content of the code block, with all whitespace preserved.
 * @property language The optional language identifier specified for the code block, which can be
 *   used for syntax highlighting by the rendering system. Will be null for indented code blocks
 *   or fenced blocks without a specified language.
 */
@Immutable
data class CodeBlock(
    override val metadata: NodeMetadata,
    val content: String,
    val language: String?,
) : ComposableStableNode()
