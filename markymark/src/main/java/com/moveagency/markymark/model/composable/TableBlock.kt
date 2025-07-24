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
import com.moveagency.markymark.model.annotated.AnnotatedStableNode
import kotlinx.collections.immutable.ImmutableList

/**
 * Represents a Github Flavoured Markdown table. Mapped from [TableBlock][com.vladsch.flexmark.ext.tables.TableBlock].
 *
 * Tables are used to organize and present data in a structured grid format with rows and columns.
 * They are particularly useful for displaying tabular data, comparisons, or any information that
 * benefits from a structured layout.
 *
 * __Basic Syntax:__
 *
 * ```markdown
 * | Header 1 | Header 2 | Header 3 |
 * | -------- | -------- | -------- |
 * | Cell 1   | Cell 2   | Cell 3   |
 * | Cell 4   | Cell 5   | Cell 6   |
 * ```
 *
 * __Column Alignment:__
 *
 * You can align text in columns by adding colons (`:`) to the separator line:
 *
 * ```markdown
 * | Left-aligned | Center-aligned | Right-aligned |
 * | :----------- | :-----------: | ------------: |
 * | Left         | Center        | Right         |
 * ```
 *
 * - `:---` Left alignment (default)
 * - `:---:` Center alignment
 * - `---:` Right alignment
 *
 * __Formatting Within Tables:__
 *
 * Tables can contain inline Markdown formatting:
 *
 * ```markdown
 * | Formatting | Example |
 * | ---------- | ------- |
 * | **Bold**   | Bold text |
 * | *Italic*   | Italic text |
 * | `Code`     | Code text |
 * | [Link](https://example.com) | Link text |
 * ```
 *
 * __Escaping Pipe Characters:__
 *
 * To include a pipe character (`|`) within a cell, escape it with a backslash:
 *
 * ```markdown
 * | Column 1 | Column 2 |
 * | -------- | -------- |
 * | A\|B     | C        |
 * ```
 *
 * __Rendering Behavior:__
 *
 * This library renders tables with the following characteristics:
 *
 * - Tables are horizontally scrollable when they exceed the available width
 * - The header row (first row) has distinct styling from the body rows
 * - Cell content is aligned according to the column alignment (Start, Center, End)
 * - Dividers are drawn between rows and columns
 * - An outline (border) is drawn around the entire table
 * - Cell dimensions adapt to content but can be constrained by minimum and maximum sizes
 * - Text styling can be customized separately for header and body cells
 *
 * __Important Notes:__
 *
 * - Tables must have at least one header row and a separator row
 * - The separator row must contain at least one hyphen (`-`) in each cell
 * - The number of cells in each row should be consistent
 * - Tables without proper formatting may not render correctly
 * - Very wide tables will enable horizontal scrolling rather than wrapping
 *
 * For more details, see the [Github Flavoured Markdown guide](https://docs.github.com/en/get-started/writing-on-github/working-with-advanced-formatting/organizing-information-with-tables).
 *
 * @property metadata Contains information about this table node, including its position in the
 *   document hierarchy and any additional metadata.
 * @property head The header row of the table, which typically contains column titles. This row
 *   is styled differently from the body rows and is always the first row in the table.
 * @property body The list of body rows in the table, containing the actual data. These rows
 *   are styled according to the body styling configuration.
 */
@Immutable
data class TableBlock(
    override val metadata: NodeMetadata,
    val head: TableRow,
    val body: ImmutableList<TableRow>,
) : ComposableStableNode() {

    /**
     * Represents a Markdown table row. Mapped from [TableRow][com.vladsch.flexmark.ext.tables.TableRow].
     *
     * A table row is a horizontal group of cells in a table. Each row contains one or more cells,
     * and rows are separated by newlines in the Markdown syntax.
     *
     * __Syntax:__
     *
     * ```markdown
     * | Cell 1 | Cell 2 | Cell 3 |
     * ```
     *
     * __Rendering Behavior:__
     *
     * - Header rows (the first row in a table) are styled differently from body rows
     * - Rows have consistent height based on the tallest cell in the row
     * - Horizontal dividers are drawn between rows
     * - The first row is always treated as a header row with special styling
     *
     * For more details on table syntax, see [TableBlock].
     *
     * @property cells The list of cells contained in this row. Each cell can contain formatted
     *   text and other inline elements.
     */
    @Immutable
    data class TableRow(val cells: ImmutableList<TableCell>)

    /**
     * Represents a Markdown table cell. Mapped from [TableCell][com.vladsch.flexmark.ext.tables.TableCell].
     *
     * A table cell is an individual data container within a table row. Cells can contain text
     * and other inline Markdown elements like bold, italic, links, or code.
     *
     * __Syntax:__
     *
     * ```markdown
     * | Cell content |
     * ```
     *
     * __Cell Alignment:__
     *
     * Cell alignment is determined by the position of colons in the separator row:
     *
     * ```markdown
     * | Left-aligned | Center-aligned | Right-aligned |
     * | :----------- | :-----------: | ------------: |
     * ```
     *
     * __Rendering Behavior:__
     *
     * - Cells are rendered with padding defined by the theme's cell padding
     * - Text alignment follows the specified [alignment] (Start, Center, or End)
     * - Vertical dividers are drawn between cells
     * - Cell width adapts to content but can be constrained by minimum and maximum sizes
     * - Cells in the header row have distinct styling from cells in body rows
     *
     * For more details on table syntax, see [TableBlock].
     *
     * @property children The list of annotated nodes contained within this cell. These can be
     *   various inline elements like text, emphasis, links, etc., that make up the content of
     *   the cell.
     * @property alignment The text alignment for this cell, represented by the [Alignment] enum.
     *   This determines how text is positioned horizontally within the cell.
     */
    @Immutable
    data class TableCell(
        val children: ImmutableList<AnnotatedStableNode>,
        val alignment: Alignment,
    ) {

        /**
         * Represents the text alignment options for table cells.
         *
         * The alignment is determined by the position of colons in the separator row of the
         * Markdown table syntax.
         */
        enum class Alignment {

            /**
             * Left-aligned text (default).
             *
             * __Syntax:__
             *
             * ```markdown
             * | :---------- |
             * ```
             *
             * Or simply:
             *
             * ```markdown
             * | ---------- |
             * ```
             *
             * Text will be aligned to the left edge of the cell.
             */
            Start,

            /**
             * Center-aligned text.
             *
             * __Syntax:__
             *
             * ```markdown
             * | :---------: |
             * ```
             *
             * Text will be centered horizontally within the cell.
             */
            Center,

            /**
             * Right-aligned text.
             *
             * __Syntax:__
             *
             * ```markdown
             * | ----------: |
             * ```
             *
             * Text will be aligned to the right edge of the cell.
             */
            End,
        }
    }
}
