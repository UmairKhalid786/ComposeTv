package com.techlads.content.data

object FakeMoviesDataProvider {
    val movies = listOf(
        MovieDto(
            id = 0,
            title = "Dune: Part Two",
            overview = "Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family.",
            posterPath = "https://images.unsplash.com/photo-1729433321272-9243b22c5f6e",
            backdropPath = "https://images.unsplash.com/photo-1718191983833-9f07f72dba26"
        ), MovieDto(
            id = 1,
            title = "Killers of the Flower Moon",
            overview = "Members of the Osage tribe are murdered under mysterious circumstances in the 1920s, prompting a major F.B.I. investigation.",
            posterPath = "https://images.unsplash.com/photo-1719937206094-8de79c912f40",
            backdropPath = "https://images.unsplash.com/photo-1696073869327-f5b41b4f8121"
        ), MovieDto(
            id = 2,
            title = "Venom 3: Let There Be Carnage",
            overview = "Eddie Brock and Venom face a new threat in the form of Cletus Kasady, a serial killer who becomes the host of the symbiote Carnage.",
            posterPath = "https://media.outnow.ch/Movies/Bilder/2024/Venom3/002.jpg",
            backdropPath = "https://media.outnow.ch/Movies/Bilder/2024/Venom3/001.jpg"
        ), MovieDto(
            id = 3,
            title = "Napoleon",
            overview = "An epic historical drama charting the rise and fall of French Emperor Napoleon Bonaparte, starring Joaquin Phoenix.",
            posterPath = "https://media.outnow.ch/Movies/Bilder/2023/Napoleon/005.jpg",
            backdropPath = "https://media.outnow.ch/Movies/Bilder/2023/Napoleon/001.jpg"
        ), MovieDto(
            id = 4,
            title = "Spider-Man: Across the Spider-Verse",
            overview = "Miles Morales reunites with Gwen Stacy and encounters a team of Spider-People to face a new threat across the multiverse.",
            posterPath = "https://64.media.tumblr.com/914bfbfdaeb0757c84c765d53bd747e6/tumblr_ms28wyKCs61qkd0zgo6_1280.png",
            backdropPath = "https://images.unsplash.com/photo-1683053145862-090e207614d9"
        ), MovieDto(
            id = 5,
            title = "The Menu",
            overview = "A young couple travels to a remote island to eat at an exclusive restaurant where the chef has prepared a lavish menu with shocking surprises.",
            posterPath = "https://film-grab.com/wp-content/uploads/2024/07/Diner-17.jpg",
            backdropPath = "https://film-grab.com/wp-content/uploads/2024/07/Diner-10.jpg"
        ), MovieDto(
            id = 6,
            title = "Lovers Rock",
            overview = "A romantic and immersive story set at a blues party in 1980s London.",
            posterPath = "https://film-grab.com/wp-content/uploads/2024/08/Lovers-Rock-17.jpg",
            backdropPath = "https://film-grab.com/wp-content/uploads/2024/08/Lovers-Rock-23.jpg"
        ), MovieDto(
            id = 7,
            title = "Cuckoo",
            overview = "A young woman's idyllic summer takes a dark turn when she suspects she's being stalked by a sinister presence.",
            posterPath = "https://film-grab.com/wp-content/uploads/2024/09/Cuckoo-30.jpg",
            backdropPath = "https://film-grab.com/wp-content/uploads/2024/09/Cuckoo-06.jpg"
        ), MovieDto(
            id = 8,
            title = "The Bloody Judge",
            overview = "A corrupt and sadistic judge terrorizes 17th-century England with his brutal methods.",
            posterPath = "https://film-grab.com/wp-content/uploads/2024/08/The-Bloody-Judge-37.jpg",
            backdropPath = "https://film-grab.com/wp-content/uploads/2024/08/The-Bloody-Judge-15.jpg"
        ), MovieDto(
            id = 9,
            title = "Oppenheimer",
            overview = "The story of American scientist J. Robert Oppenheimer and his role in the development of the atomic bomb during World War II.",
            posterPath = "https://images.unsplash.com/photo-1698235389013-507bcb014c81",
            backdropPath = "https://images.unsplash.com/photo-1698235388791-04f8371dff4c"
        ), MovieDto(
            id = 10,
            title = "Barbie",
            overview = "Barbie and Ken leave the perfect world of Barbie Land to discover the joys and challenges of the real world.",
            posterPath = "https://images.unsplash.com/photo-1692466020868-70f2abbd7b41",
            backdropPath = "https://images.unsplash.com/photo-1692466030561-c03b2ff7b8ef"
        ), MovieDto(
            id = 11,
            title = "Poor Things",
            overview = "A young woman, brought back to life by an unorthodox scientist, embarks on a journey of self-discovery and liberation.",
            posterPath = "https://images.unsplash.com/photo-1699648628910-01fd6035f28a",
            backdropPath = "https://images.unsplash.com/photo-1699648629061-0d98b4e2a7af"
        ), MovieDto(
            id = 12,
            title = "Everything Everywhere All at Once",
            overview = "A Chinese immigrant woman is swept up in an insane multiverse adventure, exploring alternate realities and her own regrets.",
            posterPath = "https://images.unsplash.com/photo-1680364358650-41726ddf88e3",
            backdropPath = "https://images.unsplash.com/photo-1680364360209-f1a2c85ff913"
        ), MovieDto(
            id = 13,
            title = "The Batman",
            overview = "Batman uncovers corruption in Gotham City that connects to his own family while facing the Riddler, a serial killer targeting the city's elite.",
            posterPath = "https://images.unsplash.com/photo-1647849476694-870c6d19f9f9",
            backdropPath = "https://images.unsplash.com/photo-1647849469012-88f07abf3fc4"
        ), MovieDto(
            id = 14,
            title = "Tenet",
            overview = "Armed with only one word — Tenet — and fighting for the survival of the world, a protagonist journeys through a twilight world of international espionage.",
            posterPath = "https://images.unsplash.com/photo-1619108432491-18d2a8fa7c54",
            backdropPath = "https://images.unsplash.com/photo-1619108444025-6e1f7329b7d7"
        )
    )
    val movieDetails = listOf(
        MovieResponse(
            id = 0,
            title = "Dune: Part Two",
            adult = false,
            budget = 165_000_000,
            overview = "Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family.",
            genre = listOf(
                GenreDto(1, "Science Fiction"), GenreDto(2, "Adventure"), GenreDto(3, "Drama")
            ),
            posterPath = "https://images.unsplash.com/photo-1729433321272-9243b22c5f6e",
            releaseDate = "2024-03-01",
            backdropPath = "https://images.unsplash.com/photo-1718191983833-9f07f72dba26",
            originalTitle = "Dune: Part Two",
            spokenLanguages = listOf(
                LanguageDto("en", "English", "English"), LanguageDto("fr", "French", "Français")
            )
        ), MovieResponse(
            id = 1,
            title = "Killers of the Flower Moon",
            adult = false,
            budget = 200_000_000,
            overview = "Members of the Osage tribe are murdered under mysterious circumstances in the 1920s, prompting a major F.B.I. investigation.",
            genre = listOf(
                GenreDto(1, "Crime"), GenreDto(2, "Western"), GenreDto(3, "Drama")
            ),
            posterPath = "https://images.unsplash.com/photo-1719937206094-8de79c912f40",
            releaseDate = "2023-10-20",
            backdropPath = "https://images.unsplash.com/photo-1696073869327-f5b41b4f8121",
            originalTitle = "Killers of the Flower Moon",
            spokenLanguages = listOf(LanguageDto("en", "English", "English"))
        ), MovieResponse(
            id = 2,
            title = "Venom 3: Let There Be Carnage",
            adult = false,
            budget = 110_000_000,
            overview = "Eddie Brock and Venom face a new threat in the form of Cletus Kasady, a serial killer who becomes the host of the symbiote Carnage.",
            genre = listOf(
                GenreDto(1, "Action"), GenreDto(2, "Science Fiction")
            ),
            posterPath = "https://media.outnow.ch/Movies/Bilder/2024/Venom3/002.jpg",
            releaseDate = "2024-11-08",
            backdropPath = "https://media.outnow.ch/Movies/Bilder/2024/Venom3/001.jpg",
            originalTitle = "Venom 3: Let There Be Carnage",
            spokenLanguages = listOf(LanguageDto("en", "English", "English"))
        ), MovieResponse(
            id = 3,
            title = "Napoleon",
            adult = false,
            budget = 130_000_000,
            overview = "An epic historical drama charting the rise and fall of French Emperor Napoleon Bonaparte, starring Joaquin Phoenix.",
            genre = listOf(
                GenreDto(1, "History"), GenreDto(2, "Biography"), GenreDto(3, "War")
            ),
            posterPath = "https://media.outnow.ch/Movies/Bilder/2023/Napoleon/005.jpg",
            releaseDate = "2023-11-22",
            backdropPath = "https://media.outnow.ch/Movies/Bilder/2023/Napoleon/001.jpg",
            originalTitle = "Napoleon",
            spokenLanguages = listOf(
                LanguageDto("en", "English", "English"), LanguageDto("fr", "French", "Français")
            )
        ), MovieResponse(
            id = 4,
            title = "Spider-Man: Across the Spider-Verse",
            adult = false,
            budget = 100_000_000,
            overview = "Miles Morales reunites with Gwen Stacy and encounters a team of Spider-People to face a new threat across the multiverse.",
            genre = listOf(
                GenreDto(1, "Animation"), GenreDto(2, "Action"), GenreDto(3, "Adventure")
            ),
            posterPath = "https://64.media.tumblr.com/914bfbfdaeb0757c84c765d53bd747e6/tumblr_ms28wyKCs61qkd0zgo6_1280.png",
            releaseDate = "2023-06-02",
            backdropPath = "https://images.unsplash.com/photo-1683053145862-090e207614d9",
            originalTitle = "Spider-Man: Across the Spider-Verse",
            spokenLanguages = listOf(LanguageDto("en", "English", "English"))
        ), MovieResponse(
            id = 5,
            title = "The Menu",
            adult = true,
            budget = 30_000_000,
            overview = "A young couple travels to a remote island to eat at an exclusive restaurant where the chef has prepared a lavish menu with shocking surprises.",
            genre = listOf(
                GenreDto(1, "Horror"), GenreDto(2, "Thriller")
            ),
            posterPath = "https://film-grab.com/wp-content/uploads/2024/07/Diner-17.jpg",
            releaseDate = "2022-11-18",
            backdropPath = "https://film-grab.com/wp-content/uploads/2024/07/Diner-10.jpg",
            originalTitle = "The Menu",
            spokenLanguages = listOf(LanguageDto("en", "English", "English"))
        ), MovieResponse(
            id = 6,
            title = "Lovers Rock",
            adult = false,
            budget = 8_000_000,
            overview = "A romantic and immersive story set at a blues party in 1980s London.",
            genre = listOf(
                GenreDto(1, "Romance"), GenreDto(2, "Drama"), GenreDto(3, "Music")
            ),
            posterPath = "https://film-grab.com/wp-content/uploads/2024/08/Lovers-Rock-17.jpg",
            releaseDate = "2020-11-27",
            backdropPath = "https://film-grab.com/wp-content/uploads/2024/08/Lovers-Rock-23.jpg",
            originalTitle = "Lovers Rock",
            spokenLanguages = listOf(LanguageDto("en", "English", "English"))
        ), MovieResponse(
            id = 7,
            title = "Cuckoo",
            adult = false,
            budget = 12_000_000,
            overview = "A young woman's idyllic summer takes a dark turn when she suspects she's being stalked by a sinister presence.",
            genre = listOf(
                GenreDto(1, "Horror"), GenreDto(2, "Thriller"), GenreDto(3, "Mystery")
            ),
            posterPath = "https://film-grab.com/wp-content/uploads/2024/09/Cuckoo-30.jpg",
            releaseDate = "2023-10-05",
            backdropPath = "https://film-grab.com/wp-content/uploads/2024/09/Cuckoo-06.jpg",
            originalTitle = "Cuckoo",
            spokenLanguages = listOf(LanguageDto("en", "English", "English"))
        ), MovieResponse(
            id = 8,
            title = "The Bloody Judge",
            adult = true,
            budget = 2_500_000,
            overview = "A corrupt and sadistic judge terrorizes 17th-century England with his brutal methods.",
            genre = listOf(
                GenreDto(1, "Horror"), GenreDto(2, "History")
            ),
            posterPath = "https://film-grab.com/wp-content/uploads/2024/08/The-Bloody-Judge-37.jpg",
            releaseDate = "1972-02-25",
            backdropPath = "https://film-grab.com/wp-content/uploads/2024/08/The-Bloody-Judge-15.jpg",
            originalTitle = "The Bloody Judge",
            spokenLanguages = listOf(LanguageDto("en", "English", "English"))
        ), MovieResponse(
            id = 9,
            title = "Oppenheimer",
            adult = false,
            budget = 100_000_000,
            overview = "The story of J. Robert Oppenheimer and his role in the development of the atomic bomb.",
            genre = listOf(
                GenreDto(1, "Biography"), GenreDto(2, "Drama"), GenreDto(3, "History")
            ),
            posterPath = "https://images.unsplash.com/photo-1698235389013-507bcb014c81",
            releaseDate = "2023-07-21",
            backdropPath = "https://images.unsplash.com/photo-1698235388791-04f8371dff4c",
            originalTitle = "Oppenheimer",
            spokenLanguages = listOf(LanguageDto("en", "English", "English"))
        )
    )
    val movieVideos = listOf(
        MovieVideosResponse(
            id = 0,
            results = listOf(
                VideoDto(
                    name = "Dune: Part Two | Official Trailer",
                    size = 1080,
                    site = "YouTube",
                    type = "Trailer",
                    key = "Way9Dexny3w",
                    official = true,
                    publishedAt = "2024-02-15T10:00:00Z",
                    iso639 = "en",
                    iso3166 = "US"
                ),
                VideoDto(
                    name = "Behind the Scenes: Creating Arrakis",
                    size = 720,
                    site = "YouTube",
                    type = "Featurette",
                    key = "hC2sWnF0w-s",
                    official = false,
                    publishedAt = "2024-02-25T09:00:00Z",
                    iso639 = "en",
                    iso3166 = "US"
                )
            )
        ),
        MovieVideosResponse(
            id = 1,
            results = listOf(
                VideoDto(
                    name = "Killers of the Flower Moon | Official Trailer",
                    size = 1080,
                    site = "YouTube",
                    type = "Trailer",
                    key = "EP34Yoxs3FQ",
                    official = true,
                    publishedAt = "2023-07-06T12:00:00Z",
                    iso639 = "en",
                    iso3166 = "US"
                )
            )
        ),
        MovieVideosResponse(
            id = 2,
            results = listOf(
                VideoDto(
                    name = "Venom 3 | Official Trailer",
                    size = 1080,
                    site = "YouTube",
                    type = "Trailer",
                    key = "u9Mv98Gr5pY",
                    official = true,
                    publishedAt = "2024-08-01T13:00:00Z",
                    iso639 = "en",
                    iso3166 = "US"
                )
            )
        ),
        MovieVideosResponse(
            id = 3,
            results = listOf(
                VideoDto(
                    name = "Napoleon | Official Trailer",
                    size = 1080,
                    site = "YouTube",
                    type = "Trailer",
                    key = "LIsfMO5Jd_w",
                    official = true,
                    publishedAt = "2023-07-10T11:30:00Z",
                    iso639 = "en",
                    iso3166 = "US"
                )
            )
        ),
        MovieVideosResponse(
            id = 4,
            results = listOf(
                VideoDto(
                    name = "Spider-Man: Across the Spider-Verse | Official Trailer",
                    size = 1080,
                    site = "YouTube",
                    type = "Trailer",
                    key = "shW9i6k8cB0",
                    official = true,
                    publishedAt = "2023-04-04T10:00:00Z",
                    iso639 = "en",
                    iso3166 = "US"
                ),
                VideoDto(
                    name = "Meet the Spider-Team",
                    size = 720,
                    site = "YouTube",
                    type = "Featurette",
                    key = "jVvQihZL6yo",
                    official = false,
                    publishedAt = "2023-05-15T12:30:00Z",
                    iso639 = "en",
                    iso3166 = "US"
                )
            )
        ),
        MovieVideosResponse(
            id = 5,
            results = listOf(
                VideoDto(
                    name = "The Menu | Official Trailer",
                    size = 1080,
                    site = "YouTube",
                    type = "Trailer",
                    key = "CAWZMssP3gM",
                    official = true,
                    publishedAt = "2022-08-10T09:45:00Z",
                    iso639 = "en",
                    iso3166 = "US"
                )
            )
        ),
        MovieVideosResponse(
            id = 6,
            results = listOf(
                VideoDto(
                    name = "Lovers Rock | Trailer",
                    size = 720,
                    site = "YouTube",
                    type = "Trailer",
                    key = "1Hx9MdmzjD0",
                    official = true,
                    publishedAt = "2020-11-13T08:30:00Z",
                    iso639 = "en",
                    iso3166 = "GB"
                )
            )
        ),
        MovieVideosResponse(
            id = 7,
            results = listOf(
                VideoDto(
                    name = "Cuckoo | Official Trailer",
                    size = 1080,
                    site = "YouTube",
                    type = "Trailer",
                    key = "W1Qm9o6F2v8",
                    official = true,
                    publishedAt = "2023-08-02T10:00:00Z",
                    iso639 = "en",
                    iso3166 = "US"
                )
            )
        ),
        MovieVideosResponse(
            id = 8,
            results = listOf(
                VideoDto(
                    name = "The Bloody Judge | 4K Restoration Trailer",
                    size = 1080,
                    site = "YouTube",
                    type = "Trailer",
                    key = "A9h5vqFzB7E",
                    official = true,
                    publishedAt = "2024-01-10T14:00:00Z",
                    iso639 = "en",
                    iso3166 = "US"
                )
            )
        ),
        MovieVideosResponse(
            id = 9,
            results = listOf(
                VideoDto(
                    name = "Oppenheimer | Official Trailer",
                    size = 1080,
                    site = "YouTube",
                    type = "Trailer",
                    key = "uYPbbksJxIg",
                    official = true,
                    publishedAt = "2023-05-08T12:00:00Z",
                    iso639 = "en",
                    iso3166 = "US"
                ),
                VideoDto(
                    name = "Inside Oppenheimer | Behind the Scenes",
                    size = 720,
                    site = "YouTube",
                    type = "Featurette",
                    key = "k1V2dA9t5UM",
                    official = false,
                    publishedAt = "2023-08-01T14:30:00Z",
                    iso639 = "en",
                    iso3166 = "US"
                )
            )
        )
    )
}