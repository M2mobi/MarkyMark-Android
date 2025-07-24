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

package com.moveagency.markymark.model

import androidx.compose.runtime.Immutable
import com.vladsch.flexmark.util.data.DataHolder
import com.vladsch.flexmark.util.data.DataKey
import com.vladsch.flexmark.util.data.DataSet
import com.vladsch.flexmark.util.data.MutableDataHolder

/**
 * This class delegates to the wrapped [DataHolder] for most of its functionality while ensuring
 * that the data cannot be modified after creation. It's designed to be safely used in Compose UI
 * and concurrent environments.
 *
 * The primary use case for this class is to store FlexMark parser configuration options in
 * [com.moveagency.markymark.MarkyMarkOptions], where immutability is important for predictable
 * behavior across recompositions.
 *
 * @property dataSet The underlying [DataHolder] that this class wraps and delegates to.
 */
@Immutable
data class ImmutableDataHolder(
    private val dataSet: DataHolder,
) : DataHolder by dataSet {

    /**
     * Gets a value from the data holder by its key.
     *
     * This method is deprecated in the Java implementation of [DataHolder],
     * but is overridden here to delegate to the wrapped [dataSet].
     *
     * @param key The [DataKey] to retrieve the value for.
     * @return The value associated with the key, or null if not found.
     */
    @Deprecated("Deprecated in Java")
    override fun <T : Any?> get(key: DataKey<T?>): T? {
        return dataSet.get(key)
    }

    /**
     * Copies all key-value pairs from this data holder into the provided [MutableDataHolder].
     *
     * This method delegates to the wrapped [dataSet] to perform the operation.
     *
     * @param dataHolder The [MutableDataHolder] to copy values into.
     * @return The [MutableDataHolder] with the values copied into it.
     */
    override fun setIn(dataHolder: MutableDataHolder): MutableDataHolder {
        return dataSet.setIn(dataHolder)
    }

    /**
     * Converts this data holder to a [DataSet].
     *
     * This method delegates to the wrapped [dataSet] to perform the conversion.
     *
     * @return A [DataSet] containing all the key-value pairs from this data holder.
     */
    override fun toDataSet(): DataSet {
        return dataSet.toDataSet()
    }
}

/**
 * Extension function to convert a [DataHolder] to an [ImmutableDataHolder].
 *
 * This function provides a convenient way to wrap any [DataHolder] in an immutable wrapper,
 * ensuring thread safety and immutability. It's particularly useful when working with
 * FlexMark configuration options that need to be used in Compose UI or concurrent environments.
 *
 * @receiver The [DataHolder] to convert.
 * @return An [ImmutableDataHolder] wrapping the receiver.
 */
fun DataHolder.toImmutableDataHolder(): ImmutableDataHolder = ImmutableDataHolder(this)
