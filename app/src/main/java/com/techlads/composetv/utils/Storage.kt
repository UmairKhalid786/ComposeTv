package com.techlads.composetv.utils

import androidx.compose.runtime.Stable
import com.techlads.composetv.features.login.withEmailPassword.Movie

object Storage {
    @Stable
    val movies = listOf(
        Movie(
            title = "Dune: Part Two",
            details = "Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family.",
            imageUrl = "https://images.unsplash.com/photo-1729433321272-9243b22c5f6e",
            metadata = "Sci-Fi/Adventure | 2023 | 2h 36m"
        ),
        Movie(
            title = "Killers of the Flower Moon",
            details = "Members of the Osage tribe in the United States are murdered under mysterious circumstances in the 1920s, prompting a major F.B.I. investigation.",
            imageUrl = "https://images.unsplash.com/photo-1719937206094-8de79c912f40",
            metadata = "Western/Crime | 2023 | 3h 26m"
        ),
        Movie(
            title = "Venom 3: Let There Be Carnage",
            details = "Eddie Brock and Venom face a new threat in the form of Cletus Kasady, a serial killer who becomes the host of the symbiote Carnage.",
            imageUrl = "https://media.outnow.ch/Movies/Bilder/2024/Venom3/002.jpg",
            metadata = "Action/Sci-Fi | 2024 | 1h 37m"
        ),
        Movie(
            title = "Napoleon",
            details = "An epic historical drama charting the rise and fall of French Emperor Napoleon Bonaparte, starring Joaquin Phoenix.",
            imageUrl = "https://media.outnow.ch/Movies/Bilder/2024/Venom3/015.jpg", // Replace with an appropriate image URL for "Napoleon"
            metadata = "Biography/History | 2023 | 2h 38m"
        ),
        Movie(
            title = "Spider-Man",
            details = "Miles Morales reunites with Gwen Stacy and encounters a team of Spider-People to face a new threat across the multiverse.",
            imageUrl = "https://64.media.tumblr.com/914bfbfdaeb0757c84c765d53bd747e6/tumblr_ms28wyKCs61qkd0zgo6_1280.png",
            metadata = "Animation/Action | 2023 | 2h 20m"
        ),
        Movie(
            title = "The Menu",
            details = "A young couple travels to a remote island to eat at an exclusive restaurant where the chef has prepared a lavish menu with some shocking surprises.",
            imageUrl = "https://film-grab.com/wp-content/uploads/2024/07/Diner-17.jpg",
            metadata = "Horror/Thriller | 2022 | 1h 47m"
        ),
        Movie(
            title = "Lovers Rock",
            details = "A romantic and immersive story set at a blues party in 1980s London.",
            imageUrl = "https://film-grab.com/wp-content/uploads/2024/08/Lovers-Rock-17.jpg",
            metadata = "Romance/Drama | 2020 | 1h 10m"
        ),
        Movie(
            title = "Cuckoo",
            details = "A young woman's idyllic summer takes a dark turn when she suspects she's being stalked by a sinister presence.",
            imageUrl = "https://film-grab.com/wp-content/uploads/2024/09/Cuckoo-30.jpg",
            metadata = "Thriller/Horror | 2023 | 1h 32m"
        ),
        Movie(
            title = "The Bloody Judge",
            details = "A corrupt and sadistic judge terrorizes 17th-century England with his brutal methods.",
            imageUrl = "https://film-grab.com/wp-content/uploads/2024/08/The-Bloody-Judge-37.jpg",
            metadata = "Horror/Historical | 1972 | 1h 37m"
        )
    )
}