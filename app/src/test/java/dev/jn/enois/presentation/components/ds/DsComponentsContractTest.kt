package dev.jn.enois.presentation.components.ds

import dev.jn.enois.ui.icons.AppIcons
import dev.jn.enois.ui.icons.Lucide
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class DsComponentsContractTest {

    @Test
    fun `tab bar items match the 5 expected tabs in order`() {
        val tabs = EnoisTabItem.entries
        assertEquals(5, tabs.size)
        assertEquals(listOf("Feed", "Comunidades", "Descobrir", "Mensagens", "Perfil"), tabs.map { it.label })
        assertEquals(
            listOf(
                AppIcons.Lucide.House,
                AppIcons.Lucide.Users,
                AppIcons.Lucide.Compass,
                AppIcons.Lucide.MessageCircle,
                AppIcons.Lucide.User,
            ),
            tabs.map { it.icon },
        )
    }

    @Test
    fun `feed segments expose ForYou and Following labels`() {
        assertEquals(
            listOf("Para você", "Seguindo"),
            EnoisFeedSegment.entries.map { it.label },
        )
    }

    @Test
    fun `list row switch trailing invokes callback with toggled state`() {
        var latestValue = false
        val trailing = ListRowTrailing.Switch(checked = false) { latestValue = it }
        trailing.onCheckedChange(true)
        assertTrue(latestValue)
    }

    @Test
    fun `user row action trailing invokes action callback`() {
        var clicked = false
        val trailing = UserRowTrailing.Action(label = "Convidar") { clicked = true }
        assertEquals("Convidar", trailing.label)
        trailing.onAction()
        assertTrue(clicked)
    }
}
