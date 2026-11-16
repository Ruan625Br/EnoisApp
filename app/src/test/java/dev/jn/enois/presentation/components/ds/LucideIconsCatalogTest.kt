package dev.jn.enois.presentation.components.ds

import androidx.compose.ui.unit.dp
import dev.jn.enois.ui.icons.AppIcons
import dev.jn.enois.ui.icons.Lucide
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class LucideIconsCatalogTest {

    @Test
    fun `catalog exposes exactly 62 unique Lucide icons with 24dp viewport`() {
        val all = AppIcons.Lucide.all
        assertEquals(62, all.size)

        val names = all.map { it.first }
        assertEquals(62, names.distinct().size)

        val vectors = all.map { it.second }
        assertEquals(62, vectors.distinct().size)

        all.forEach { (name, vector) ->
            assertTrue("Icon name should not be blank", name.isNotBlank())
            assertEquals("Icon $name defaultWidth", 24.dp, vector.defaultWidth)
            assertEquals("Icon $name defaultHeight", 24.dp, vector.defaultHeight)
            assertEquals("Icon $name viewportWidth", 24f, vector.viewportWidth)
            assertEquals("Icon $name viewportHeight", 24f, vector.viewportHeight)
            assertTrue("Icon $name should have at least one path node", vector.root.size > 0)
        }
    }
}
