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

package com.moveagency.markymark.sample.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily.Companion.Monospace
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextDecoration.Companion.Underline
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moveagency.markymark.sample.ui.theme.ColorPalette.Black
import com.moveagency.markymark.sample.ui.theme.ColorPalette.FirstAndBeyondRed
import com.moveagency.markymark.sample.ui.theme.ColorPalette.Grey
import com.moveagency.markymark.sample.ui.theme.ColorPalette.LekkerLila
import com.moveagency.markymark.sample.ui.theme.ColorPalette.LightGrey
import com.moveagency.markymark.sample.ui.theme.ColorPalette.OceanBlue
import com.moveagency.markymark.sample.ui.theme.ColorPalette.OrbYellow
import com.moveagency.markymark.sample.ui.theme.ColorPalette.White
import com.moveagency.markymark.sample.ui.theme.Shape.CodeBlockShape
import com.moveagency.markymark.sample.ui.theme.Shape.ImageShape
import com.moveagency.markymark.sample.ui.theme.Shape.QuoteShape
import com.moveagency.markymark.sample.ui.theme.Typography.Body
import com.moveagency.markymark.sample.ui.theme.Typography.Caption
import com.moveagency.markymark.sample.ui.theme.Typography.H1
import com.moveagency.markymark.sample.ui.theme.Typography.H2
import com.moveagency.markymark.sample.ui.theme.Typography.H3
import com.moveagency.markymark.sample.ui.theme.Typography.H4
import com.moveagency.markymark.sample.ui.theme.Typography.H5
import com.moveagency.markymark.sample.ui.theme.Typography.H6
import com.moveagency.markymark.sample.ui.theme.Typography.TableHeader
import com.moveagency.markymark.theme.LocalMarkyMarkTheme
import com.moveagency.markymark.theme.MarkyMarkColors
import com.moveagency.markymark.theme.list.UnorderedListItemStyle.Indicator.IndicatorDrawStyle.Fill
import com.moveagency.markymark.theme.list.UnorderedListItemStyle.Indicator.IndicatorDrawStyle.Stroke
import com.moveagency.markymark.theme.list.UnorderedListItemStyle.Indicator.Shape.Oval
import com.moveagency.markymark.theme.list.UnorderedListItemStyle.Indicator.Shape.Rectangle
import com.moveagency.markymark.theme.markyMarkTheme

private val LightColors by lazy {
    MarkyMarkColors.Builder().apply {
        headings { all = Black }

        image { caption = Black }

        rule { background = Black }

        codeBlock {
            background = OceanBlue
            text = Black
        }

        table {
            header {
                text = Black
                divider = Unspecified
            }

            body {
                text = Black
                divider = Unspecified
            }

            outline { all = Unspecified }
        }

        list {
            ordered {
                text = Black
                indicator = Black
            }

            unordered {
                text = Black
                indicator = Black
            }

            task {
                text = Black
                checked = OrbYellow
                unchecked = Grey
                checkmark = Black
            }
        }

        paragraph { text = Black }
    }
}

private val DarkColors by lazy {
    MarkyMarkColors.Builder().apply {
        headings { all = White }

        image { caption = White }

        rule { background = White }

        codeBlock {
            background = OceanBlue
            text = Black
        }

        table {
            header {
                text = White
                divider = Unspecified
            }

            body {
                text = White
                divider = Unspecified
            }

            outline { all = Unspecified }
        }

        list {
            ordered {
                text = White
                indicator = White
            }

            unordered {
                text = White
                indicator = White
            }

            task {
                text = Black
                checked = OrbYellow
                unchecked = LightGrey
                checkmark = Black
            }
        }

        paragraph { text = White }
    }
}

private val MarkyMarkTheme by lazy {
    markyMarkTheme {
        root {
            padding { all = Spacing.x2 }
        }

        styles {
            composable {
                headings {
                    h1 {
                        textStyle = H1
                        padding {
                            top = Spacing.x2
                            bottom = Spacing.x1_5
                        }
                    }

                    h2 {
                        textStyle = H2
                        padding {
                            top = Spacing.x2
                            bottom = Spacing.x1_5
                        }
                    }

                    h3 {
                        textStyle = H3
                        padding {
                            top = Spacing.x2
                            bottom = Spacing.x1_5
                        }
                    }

                    h4 {
                        textStyle = H4
                        padding {
                            top = Spacing.x1
                            bottom = Spacing.x1
                        }
                    }

                    h5 {
                        textStyle = H5
                        padding {
                            top = Spacing.x1
                            bottom = Spacing.x1
                        }
                    }

                    h6 {
                        textStyle = H6
                        padding {
                            top = Spacing.x1
                            bottom = Spacing.x1
                        }
                    }
                }

                image {
                    shape = ImageShape

                    caption { textStyle = Caption }
                }

                codeBlock {
                    textStyle = Body.copy(fontFamily = Monospace)
                    shape = CodeBlockShape

                    innerPadding { all = Spacing.x2 }
                    outerPadding { all = Spacing.x0_5 }
                }

                blockQuote {
                    shape = QuoteShape

                    innerPadding { all = Spacing.x2 }
                    outerPadding { all = Spacing.x0_5 }

                    themes {
                        theme {
                            background = LekkerLila

                            colors { include(LightColors) }
                        }

                        theme {
                            background = FirstAndBeyondRed

                            colors { include(DarkColors) }
                        }

                        theme {
                            background = OrbYellow

                            colors { include(LightColors) }
                        }
                    }
                }

                tableBlock {
                    padding { vertical = Spacing.x1 }

                    header {
                        divider { thickness = 2.dp }

                        cell {
                            textStyle = TableHeader

                            padding { horizontal = Spacing.x2 }
                        }
                    }

                    body {
                        cell {
                            textStyle = Body

                            padding { horizontal = Spacing.x2 }
                        }
                    }
                }

                listBlock {
                    levelIndent = Spacing.x3

                    ordered {
                        textStyle = Body
                        indicatorTextStyle = Body.copy(fontWeight = Medium)

                        indicatorPadding { end = Spacing.x1 }
                    }

                    unordered {
                        textStyle = Body

                        indicatorPadding { end = Spacing.x1 }

                        indicators {
                            indicator {
                                shape = Oval
                                style = Fill
                            }

                            indicator {
                                shape = Oval
                                style = Stroke(width = 1.dp)
                            }

                            indicator { shape = Rectangle }
                        }
                    }

                    task {
                        textStyle = Body

                        indicatorPadding { end = Spacing.x1 }
                    }
                }

                paragraph { textStyle = Body }
            }

            annotated {
                bold = SpanStyle(fontWeight = SemiBold)
                link = SpanStyle(textDecoration = Underline)
                code = SpanStyle(fontFamily = Monospace, background = OceanBlue)
                superscript = SpanStyle(baselineShift = BaselineShift(0.4F), fontSize = 10.sp)
                subscript = SpanStyle(baselineShift = BaselineShift(-0.2F), fontSize = 10.sp)
            }
        }
    }
}

private val LightMarkyMarkTheme by lazy {
    markyMarkTheme {
        include(MarkyMarkTheme)

        colors { include(LightColors) }
    }
}

private val DarkMarkyMarkTheme by lazy {
    markyMarkTheme {
        include(MarkyMarkTheme)

        colors { include(DarkColors) }
    }
}

@Composable
fun SampleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val markdownTheme = if (darkTheme) DarkMarkyMarkTheme else LightMarkyMarkTheme
    CompositionLocalProvider(
        LocalMarkyMarkTheme provides markdownTheme,
        content = content,
    )
}
