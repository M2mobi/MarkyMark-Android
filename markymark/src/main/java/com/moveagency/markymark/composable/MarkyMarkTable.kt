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

import android.util.Log
import androidx.annotation.Px
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.*
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.text.style.TextAlign.Companion.End
import androidx.compose.ui.text.style.TextAlign.Companion.Start
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.moveagency.markymark.composer.padding
import com.moveagency.markymark.model.NodeMetadata.Companion.Root
import com.moveagency.markymark.model.annotated.Text
import com.moveagency.markymark.model.composable.TableBlock
import com.moveagency.markymark.model.composable.TableBlock.TableCell
import com.moveagency.markymark.model.composable.TableBlock.TableRow
import com.moveagency.markymark.theme.LocalMarkyMarkTheme
import com.moveagency.markymark.theme.table.*
import kotlinx.collections.immutable.persistentListOf
import kotlin.math.roundToInt

@Composable
fun MarkyMarkTable(
    node: TableBlock,
    modifier: Modifier = Modifier,
) {
    val style = LocalMarkyMarkTheme.current.styles.composable.tableBlock
    val colors = LocalMarkyMarkColors.current.table
    val measurePolicy = remember(style) { TableBlockMeasurePolicy(style) }
    Layout(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .then(modifier)
            .padding(style.padding)
            .drawWithCache {
                onDrawBehind {
                    drawDividers(
                        columnWidths = measurePolicy.measuredColumnWidths,
                        rowHeights = measurePolicy.measuredRowHeights,
                        style = style,
                        colors = colors,
                    )
                }
            },
        measurePolicy = measurePolicy,
        content = { TableContent(node = node) },
    )
}

private fun DrawScope.drawDividers(
    columnWidths: Map<Int, Int>,
    rowHeights: Map<Int, Int>,
    style: TableBlockStyle,
    colors: TableColors,
) {
    drawBodyDividers(
        columnWidths = columnWidths,
        rowHeights = rowHeights,
        style = style,
        color = colors.body.divider,
    )
    drawHeaderDivider(
        style = style.header.divider,
        yOffset = rowHeights.getValue(0) + toSafePx(style.outline.top.thickness),
        color = colors.header.divider,
    )
    drawOutlines(
        style = style.outline,
        colors = colors.outline,
    )
}

private fun DrawScope.drawBodyDividers(
    columnWidths: Map<Int, Int>,
    rowHeights: Map<Int, Int>,
    style: TableBlockStyle,
    color: Color,
) {
    drawColumnDividers(
        columnWidths = columnWidths,
        style = style,
        color = color,
    )
    drawRowDividers(
        rowHeights = rowHeights,
        style = style,
        color = color,
    )
}

@Suppress("NestedBlockDepth")
private fun DrawScope.drawColumnDividers(
    columnWidths: Map<Int, Int>,
    style: TableBlockStyle,
    color: Color,
) {
    val dividerStyle = style.body.verticalDivider
    if (!isDividerWorthDrawing(dividerStyle, color)) return

    val leftOutlineThickness = toSafePx(style.outline.left.thickness)
    val bodyDividerThickness = toSafePx(dividerStyle.thickness)
    for (column in 1 until columnWidths.size) {
        var x = leftOutlineThickness
        for (i in 0 until column) {
            if (i > 0) x += bodyDividerThickness
            x += columnWidths.getValue(i)
        }

        drawRect(
            color = color,
            topLeft = Offset(x = x, y = 0F),
            size = Size(width = bodyDividerThickness, height = size.height),
        )
    }
}

@Suppress("NestedBlockDepth")
private fun DrawScope.drawRowDividers(
    rowHeights: Map<Int, Int>,
    style: TableBlockStyle,
    color: Color,
) {
    val dividerStyle = style.body.horizontalDivider
    if (!isDividerWorthDrawing(dividerStyle, color)) return

    val topOutlineThickness = toSafePx(style.outline.top.thickness)
    val headerDividerThickness = toSafePx(style.header.divider.thickness)
    val bodyDividerThickness = toSafePx(dividerStyle.thickness)
    for (row in 2 until rowHeights.size) {
        var y = topOutlineThickness + headerDividerThickness
        for (i in 0 until row) {
            if (i > 1) y += bodyDividerThickness
            y += rowHeights.getValue(i)
        }

        drawRect(
            color = color,
            topLeft = Offset(x = 0F, y = y),
            size = Size(width = size.width, height = bodyDividerThickness),
        )
    }
}

private fun DrawScope.drawHeaderDivider(
    style: TableDividerStyle,
    yOffset: Float,
    color: Color,
) {
    if (!isDividerWorthDrawing(style, color)) return

    drawRect(
        color = color,
        topLeft = Offset(x = 0F, y = yOffset),
        size = Size(
            width = size.width,
            height = toSafePx(style.thickness),
        )
    )
}

private fun DrawScope.drawOutlines(
    style: TableOutlineStyle,
    colors: TableOutlineColors,
) {
    // left
    val leftThickness = toSafePx(style.left.thickness)
    drawOutline(
        style = style.left,
        color = colors.left,
        size = Size(
            width = size.width,
            height = leftThickness,
        ),
        topLeft = Offset(
            x = 0F,
            y = size.height - leftThickness,
        ),
    )

    // top
    val topThickness = toSafePx(style.top.thickness)
    drawOutline(
        style = style.top,
        color = colors.top,
        size = Size(
            width = size.width,
            height = topThickness,
        ),
    )

    // right
    drawOutline(
        style = style.right,
        color = colors.right,
        size = Size(
            width = toSafePx(style.right.thickness),
            height = size.height,
        ),
    )

    // bottom
    val bottomThickness = toSafePx(style.bottom.thickness)
    drawOutline(
        style = style.bottom,
        color = colors.bottom,
        size = Size(
            width = bottomThickness,
            height = size.height,
        ),
        topLeft = Offset(
            x = size.width - bottomThickness,
            y = 0F,
        ),
    )
}

private fun DrawScope.drawOutline(
    style: TableDividerStyle,
    color: Color,
    size: Size,
    topLeft: Offset = Offset.Zero,
) {
    if (!isDividerWorthDrawing(style, color)) return

    drawRect(
        color = color,
        topLeft = topLeft,
        size = size,
    )
}

class TableBlockMeasurePolicy(
    private val style: TableBlockStyle,
) : MeasurePolicy {

    var measuredColumnWidths by mutableStateOf(emptyMap<Int, Int>())
    var measuredRowHeights by mutableStateOf(emptyMap<Int, Int>())

    @Suppress("LongMethod")
    override fun MeasureScope.measure(measurables: List<Measurable>, constraints: Constraints): MeasureResult {
        val columnWidths = mutableMapOf<Int, Int>()
        val rowHeights = mutableMapOf<Int, Int>()
        val placeableCells = mutableListOf<PlaceableTableCell>()

        for (measurable in measurables) {
            Log.d("[!!!]", "${measurable.layoutId}")
            val spec = measurable.layoutId as TableCellSpec
            columnWidths[spec.column] = maxOf(
                columnWidths[spec.column] ?: style.cellMinWidth.roundToPx(),
                measurable.maxIntrinsicWidth(constraints.maxHeight),
            ).coerceAtMost(style.cellMaxWidth.roundToPx())
            rowHeights[spec.row] = maxOf(
                rowHeights[spec.row] ?: style.cellMinWidth.roundToPx(),
                measurable.maxIntrinsicHeight(constraints.maxWidth),
            ).coerceAtMost(style.cellMaxHeight.roundToPx())
        }

        for (measurable in measurables) {
            val spec = measurable.layoutId as TableCellSpec
            val width = columnWidths.getValue(spec.column)
            val height = rowHeights.getValue(spec.row)
            val placeable = measurable.measure(Constraints.fixed(width, height))
            placeableCells += PlaceableTableCell(
                spec = spec,
                placeable = placeable,
            )
        }

        measuredColumnWidths = columnWidths
        measuredRowHeights = rowHeights

        val leftOutlineThickness = toSafePx(style.outline.left.thickness)
        val topOutlineThickness = toSafePx(style.outline.top.thickness)
        val rightOutlineThickness = toSafePx(style.outline.right.thickness)
        val bottomOutlineThickness = toSafePx(style.outline.bottom.thickness)
        val headerDividerThickness = toSafePx(style.header.divider.thickness)
        val bodyHorizontalThickness = toSafePx(style.body.horizontalDivider.thickness)
        val bodyVerticalThickness = toSafePx(style.body.verticalDivider.thickness)

        val outlineHorizontalSum = topOutlineThickness + bottomOutlineThickness
        val outlineVerticalSum = leftOutlineThickness + rightOutlineThickness

        val columnSum = columnWidths.values.sum()
        val columnBodySum = bodyVerticalThickness * (columnWidths.size - 1)
        val width = columnSum + columnBodySum + outlineVerticalSum

        val rowSum = rowHeights.values.sum()
        val rowBodySum = bodyHorizontalThickness * (rowHeights.size - 2)
        val height = rowSum + rowBodySum + headerDividerThickness + outlineHorizontalSum

        return layout(width.roundToInt(), height.roundToInt()) {
            for ((spec, placeable) in placeableCells) {
                val column = spec.column
                val row = spec.row
                var x = leftOutlineThickness
                for (i in 0 until column) {
                    if (i < columnWidths.size - 1) {
                        x += bodyVerticalThickness
                    }
                    x += columnWidths.getValue(i)
                }
                var y = topOutlineThickness
                for (i in 0 until row) {
                    if (i == 0) {
                        y += headerDividerThickness
                    }
                    if (i > 0 && i < rowHeights.size - 1) {
                        y += bodyHorizontalThickness
                    }
                    y += rowHeights.getValue(i)
                }
                placeable.placeRelative(IntOffset(x.roundToInt(), y.roundToInt()))
            }
        }
    }
}

@Composable
private fun TableContent(
    node: TableBlock,
) {
    val style = LocalMarkyMarkTheme.current.styles.composable.tableBlock
    val colors = LocalMarkyMarkColors.current.table
    TableRow(rowIndex = 0, row = node.head, cellStyle = style.header.cell, textColor = colors.header.text)
    for ((index, row) in node.body.withIndex()) {
        TableRow(rowIndex = index + 1, row = row, cellStyle = style.body.cell, textColor = colors.body.text)
    }
}

@Composable
private fun TableRow(
    rowIndex: Int,
    row: TableRow,
    cellStyle: TableCellStyle,
    textColor: Color,
) {
    for ((index, cell) in row.cells.withIndex()) {
        TableCell(
            row = rowIndex,
            column = index,
            style = cellStyle,
            textColor = textColor,
            cell = cell,
        )
    }
}

@Composable
private fun TableCell(
    row: Int,
    column: Int,
    style: TableCellStyle,
    textColor: Color,
    cell: TableCell,
) {
    CompositionLocalProvider(LocalContentColor provides textColor) {
        // Needed because the SelectionContainer gets rid of the layoutId for some reason.
        Box(modifier = Modifier.layoutId(TableCellSpec(row = row, column = column))) {
            MarkyMarkText(
                modifier = Modifier.padding(style.padding),
                nodes = cell.children,
                style = style.textStyle.copy(
                    textAlign = when (cell.alignment) {
                        TableCell.Alignment.Start -> Start
                        TableCell.Alignment.Center -> Center
                        TableCell.Alignment.End -> End
                    },
                ),
            )
        }
    }
}

/**
 * Helper class for identifying a table cell during measuring.
 */
@Immutable
private data class TableCellSpec(
    val row: Int,
    val column: Int,
)

/**
 * Helper class for laying out a table cell.
 */
@Immutable
private data class PlaceableTableCell(
    val spec: TableCellSpec,
    val placeable: Placeable,
)

@Px
private fun Density.toSafePx(dp: Dp): Float = if (dp == Dp.Unspecified) 0F else dp.toPx()

@Preview
@Composable
private fun PreviewTableBlock() {
    MarkyMarkTable(
        node = TableBlock(
            metadata = Root,
            head = TableRow(
                cells = persistentListOf(
                    TableCell(
                        children = persistentListOf(
                            Text(
                                metadata = Root,
                                content = "Header, cell 1",
                            )
                        ),
                        alignment = TableCell.Alignment.Center,
                    ),
                    TableCell(
                        children = persistentListOf(
                            Text(
                                metadata = Root,
                                content = "Header, cell 2",
                            )
                        ),
                        alignment = TableCell.Alignment.Center,
                    ),
                    TableCell(
                        children = persistentListOf(
                            Text(
                                metadata = Root,
                                content = "Header, cell 3",
                            )
                        ),
                        alignment = TableCell.Alignment.Center,
                    ),
                    TableCell(
                        children = persistentListOf(
                            Text(
                                metadata = Root,
                                content = "Header, cell 4",
                            )
                        ),
                        alignment = TableCell.Alignment.Center,
                    ),
                ),
            ),
            body = persistentListOf(
                TableRow(
                    cells = persistentListOf(
                        TableCell(
                            children = persistentListOf(
                                Text(
                                    metadata = Root,
                                    content = "Row 1, cell 1",
                                )
                            ),
                            alignment = TableCell.Alignment.Start,
                        ),
                        TableCell(
                            children = persistentListOf(
                                Text(
                                    metadata = Root,
                                    content = "Row 1, cell 2",
                                )
                            ),
                            alignment = TableCell.Alignment.Start,
                        ),
                        TableCell(
                            children = persistentListOf(
                                Text(
                                    metadata = Root,
                                    content = "Row 1, cell 3",
                                )
                            ),
                            alignment = TableCell.Alignment.Start,
                        ),
                        TableCell(
                            children = persistentListOf(
                                Text(
                                    metadata = Root,
                                    content = "Row 1, cell 4",
                                )
                            ),
                            alignment = TableCell.Alignment.Start,
                        ),
                    ),
                ),
                TableRow(
                    cells = persistentListOf(
                        TableCell(
                            children = persistentListOf(
                                Text(
                                    metadata = Root,
                                    content = "Row 2, cell 1",
                                )
                            ),
                            alignment = TableCell.Alignment.End,
                        ),
                        TableCell(
                            children = persistentListOf(
                                Text(
                                    metadata = Root,
                                    content = "Row 2, cell 2",
                                )
                            ),
                            alignment = TableCell.Alignment.End,
                        ),
                        TableCell(
                            children = persistentListOf(
                                Text(
                                    metadata = Root,
                                    content = "Row 2, cell 3",
                                )
                            ),
                            alignment = TableCell.Alignment.End,
                        ),
                        TableCell(
                            children = persistentListOf(
                                Text(
                                    metadata = Root,
                                    content = "Row 2, cell 4",
                                )
                            ),
                            alignment = TableCell.Alignment.End,
                        ),
                    ),
                ),
                TableRow(
                    cells = persistentListOf(
                        TableCell(
                            children = persistentListOf(
                                Text(
                                    metadata = Root,
                                    content = "Row 3, cell 1",
                                )
                            ),
                            alignment = TableCell.Alignment.Center,
                        ),
                        TableCell(
                            children = persistentListOf(
                                Text(
                                    metadata = Root,
                                    content = "Row 3, cell 2",
                                )
                            ),
                            alignment = TableCell.Alignment.Center,
                        ),
                        TableCell(
                            children = persistentListOf(
                                Text(
                                    metadata = Root,
                                    content = "Row 3, cell 3",
                                )
                            ),
                            alignment = TableCell.Alignment.Center,
                        ),
                        TableCell(
                            children = persistentListOf(
                                Text(
                                    metadata = Root,
                                    content = "Row 3, cell 4",
                                )
                            ),
                            alignment = TableCell.Alignment.Center,
                        ),
                    ),
                ),
            ),
        ),
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
    )
}

private fun isDividerWorthDrawing(
    style: TableDividerStyle,
    color: Color,
) = color == Transparent || color == Color.Unspecified || style.thickness == Dp.Unspecified || style.thickness == 0.dp
