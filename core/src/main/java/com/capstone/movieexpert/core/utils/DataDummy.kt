package com.capstone.movieexpert.core.utils

import com.capstone.movieexpert.core.data.source.local.entity.MovieEntity
import com.capstone.movieexpert.core.data.source.local.entity.TvShowEntity
import com.capstone.movieexpert.core.data.source.remote.response.SeasonResponse

object DataDummy {
    fun generateDummyMovies(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                id = 399566,
                name =  "Godzilla vs. Kong",
                desc = "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                backdrop = "https://image.tmdb.org/t/p/original/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                date = "2021-03-24",
                popularity = 6065.197,
                rating = 8.3
            )
        )

        movies.add(
            MovieEntity(
                id = 791373,
                name =  "Zack Snyder's Justice League",
                desc = "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                backdrop = "https://image.tmdb.org/t/p/original/pcDc2WJAYGJTTvRSEIpRZwM3Ola.jpg",
                date = "2021-03-18",
                popularity = 3096.436,
                rating = 8.5
            )
        )

        movies.add(
            MovieEntity(
                id = 412656,
                name =  "Chaos Walking",
                desc = "Two unlikely companions embark on a perilous adventure through the badlands of an unexplored planet as they try to escape a dangerous and disorienting reality, where all inner thoughts are seen and heard by everyone.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/9kg73Mg8WJKlB9Y2SAJzeDKAnuB.jpg",
                backdrop = "https://image.tmdb.org/t/p/original/5NxjLfs7Bi07bfZCRl9CCnUw7AA.jpg",
                date = "2021-02-24",
                popularity = 2312.529,
                rating = 7.4
            )
        )

        movies.add(
            MovieEntity(
                id = 460465,
                name =  "Mortal Kombat",
                desc = "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/8yhtzsbBExY8mUct2GOk4LDDuGH.jpg",
                backdrop = "https://image.tmdb.org/t/p/original/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                date = "2021-04-07",
                popularity = 2301.309,
                rating = 7.6
            )
        )

        movies.add(
            MovieEntity(
                id = 527774,
                name =  "Raya and the Last Dragon",
                desc = "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
                backdrop = "https://image.tmdb.org/t/p/original/7prYzufdIOy1KCTZKVWpjBFqqNr.jpg",
                date = "2021-03-03",
                popularity = 1895.918,
                rating = 8.3
            )
        )

        movies.add(
            MovieEntity(
                id = 644083,
                name =  "Twist",
                desc =  "A Dicken’s classic brought thrillingly up to date in the teeming heartland of modern London, where a group of street smart young hustlers plan the heist of the century for the ultimate payday.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/29dCusd9PwHrbDqzxNG35WcpZpS.jpg",
                backdrop = "https://image.tmdb.org/t/p/original/jnq4fV53Px9HvUZD2bQIxtGSwS7.jpg",
                date = "2021-01-22",
                popularity = 1321.171,
                rating = 7.1
            )
        )

        movies.add(
            MovieEntity(
                id = 458576,
                name =  "Monster Hunter",
                desc = "A portal transports Cpt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/1UCOF11QCw8kcqvce8LKOO6pimh.jpg",
                backdrop = "https://image.tmdb.org/t/p/original/z8TvnEVRenMSTemxYZwLGqFofgF.jpg",
                date = "2020-12-03",
                popularity = 1190.588,
                rating = 7.1
            )
        )

        movies.add(
            MovieEntity(
                id = 464052,
                name =  "Wonder Woman 1984",
                desc = "A botched store robbery places Wonder Woman in a global battle against a powerful and mysterious ancient force that puts her powers in jeopardy.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                backdrop = "https://image.tmdb.org/t/p/original/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
                date = "2020-12-16",
                popularity = 1076.442,
                rating = 6.8
            )
        )

        movies.add(
            MovieEntity(
                id = 632357,
                name =  "The Unholy",
                desc = "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/b4gYVcl8pParX8AjkN90iQrWrWO.jpg",
                backdrop = "https://image.tmdb.org/t/p/original/zDq2pwPyt4xwSFHKUoNN2LohDWj.jpg",
                date = "2021-03-31",
                popularity = 798.655,
                rating = 5.8
            )
        )

        movies.add(
            MovieEntity(
                id = 522444,
                name =  "Black Water: Abyss",
                desc =  "An adventure-loving couple convince their friends to explore a remote, uncharted cave system in the forests of Northern Australia. With a tropical storm approaching, they abseil into the mouth of the cave, but when the caves start to flood, tensions rise as oxygen levels fall and the friends find themselves trapped. Unknown to them, the storm has also brought in a pack of dangerous and hungry crocodiles.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/95S6PinQIvVe4uJAd82a2iGZ0rA.jpg",
                backdrop = "https://image.tmdb.org/t/p/original/fRrpOILyXuWaWLmqF7kXeMVwITQ.jpg",
                date = "2020-07-09",
                popularity = 825.745,
                rating = 4.9
            )
        )

        movies.add(
            MovieEntity(
                id = 41800,
                name =  "Superman and the Mole-Men",
                desc = "Reporters Clark Kent and Lois Lane arrive in the small town of Silsby to witness the drilling of the world's deepest oil well. The drill, however, has penetrated the underground home of a race of small, furry people who then come to the surface at night to look around. The fact that they glow in the dark scares the townfolk, who form a mob, led by the vicious Luke Benson, intent on killing the strange people. Only Superman has a chance to prevent this tragedy.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/9vRltKcX4wK4ms0A6PbRz42EMdq.jpg",
                backdrop = "https://image.tmdb.org/t/p/original/pZ1mEYQJfpYzGvlSJVh8NVAdFXQ.jpg",
                date = "1951-11-23",
                popularity = 5.817,
                rating = 5.4
            )
        )

        movies.add(
            MovieEntity(
                id = 155,
                name =  "The Dark Knight",
                desc = "Batman raises the stakes in his war on crime. With the help of Lt. Jim Gordon and District Attorney Harvey Dent, Batman sets out to dismantle the remaining criminal organizations that plague the streets. The partnership proves to be effective, but they soon find themselves prey to a reign of chaos unleashed by a rising criminal mastermind known to the terrified citizens of Gotham as the Joker.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
                backdrop = "https://image.tmdb.org/t/p/original/nMKdUUepR0i5zn0y1T4CsSB5chy.jpg",
                date = "2008-07-16",
                popularity = 56.588,
                rating = 8.5
            )
        )

        movies.add(
            MovieEntity(
                id = 297802,
                name =  "Aquaman",
                desc = "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/5Kg76ldv7VxeX9YlcQXiowHgdX6.jpg",
                backdrop = "https://image.tmdb.org/t/p/original/9QusGjxcYvfPD1THg6oW3RLeNn7.jpg",
                date = "2018-12-07",
                popularity = 112.145,
                rating = 6.9
            )
        )

        movies.add(
            MovieEntity(
                id = 297761,
                name =  "Suicide Squad",
                desc = "From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government, undertaking high-risk black ops missions in exchange for commuted prison sentences.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/rbsm0i2q2uqlUSFgUAHq3xCUO4j.jpg",
                backdrop = "https://image.tmdb.org/t/p/original/dpoIQ9MN54cxuLZxDABEUOSFGY3.jpg",
                date = "2016-08-03",
                popularity = 58.991,
                rating = 5.9
            )
        )
        return movies
    }

    fun generateDummyTvShows(): List<TvShowEntity> {
        val tvShows = ArrayList<TvShowEntity>()

        tvShows.add(
            TvShowEntity(
                id = 88396,
                name = "The Falcon and the Winter Soldier",
                desc = "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                backdrop = "https://image.tmdb.org/t/p/original/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                date = "2021-03-19",
                popularity = 7013.338,
                rating = 7.9
            )
        )

        tvShows.add(
                TvShowEntity(
                id = 71712,
                name = "The Good Doctor",
                desc = "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                backdrop = "https://image.tmdb.org/t/p/original/mZjZgY6ObiKtVuKVDrnS9VnuNlE.jpg",
                date = "2017-09-25",
                popularity = 1881.58,
                rating = 8.6,
            )
        )

        tvShows.add(
                TvShowEntity(
                id = 60735,
                name = "The Flash",
                desc = "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                backdrop = "https://image.tmdb.org/t/p/original/z59kJfcElR9eHO9rJbWp4qWMuee.jpg",
                date = "2014-10-07",
                popularity = 1308.643,
                rating = 7.7,
            )
        )

        tvShows.add(
                TvShowEntity(
                id = 95557,
                name = "Invincible",
                desc = "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                backdrop = "https://image.tmdb.org/t/p/original/6UH52Fmau8RPsMAbQbjwN3wJSCj.jpg",
                date = "2021-03-26",
                popularity = 1313.434,
                rating = 8.9,
            )
        )

        tvShows.add(
                TvShowEntity(
                id = 69050,
                name = "Riverdale",
                desc = "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
                backdrop = "https://image.tmdb.org/t/p/original/qZtAf4Z1lazGQoYVXiHOrvLr5lI.jpg",
                date = "2017-01-26",
                popularity = 983.102,
                rating = 8.6
            )
        )

        tvShows.add(
                TvShowEntity(
                id = 1416,
                name = "Grey's Anatomy",
                desc = "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                backdrop = "https://image.tmdb.org/t/p/original/edmk8xjGBsYVIf4QtLY9WMaMcXZ.jpg",
                date = "2005-03-27",
                popularity = 994.98,
                rating = 8.2,
            )
        )

        tvShows.add(
                TvShowEntity(
                id = 18165,
                name = "The Vampire Diaries",
                desc = "The story of two vampire brothers obsessed with the same girl, who bears a striking resemblance to the beautiful but ruthless vampire they knew and loved in 1864.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/kLEha9zVVv8acGFKTX4gjvSR2Q0.jpg",
                backdrop = "https://image.tmdb.org/t/p/original/k7T9xRyzP41wBVNyNeLmh8Ch7gD.jpg",
                date = "2009-09-10",
                popularity = 407.362,
                rating = 8.3,
            )
        )

        tvShows.add(
                TvShowEntity(
                id = 77169,
                name = "Cobra Kai",
                desc = "This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/obLBdhLxheKg8Li1qO11r2SwmYO.jpg",
                backdrop = "https://image.tmdb.org/t/p/original/gL8myjGc2qrmqVosyGm5CWTir9A.jpg",
                date = "2018-05-02",
                popularity = 455.645,
                rating = 8.1,
            )
        )

        tvShows.add(
                TvShowEntity(
                id = 1399,
                name = "Game of Thrones",
                desc = "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                backdrop = "https://image.tmdb.org/t/p/original/suopoADq0k8YZr4dQXcU6pToj6s.jpg",
                date = "2011-04-17",
                popularity = 514.834,
                rating = 8.4,
            )
        )

        tvShows.add(
                TvShowEntity(
                id = 71694,
                name = "Snowfall",
                desc = "Los Angeles. 1983. A storm is coming and it's name is crack. Set against the infancy of the crack cocaine epidemic and its ultimate radical impact on the culture as we know it, the story follows numerous characters on a violent collision course.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/orvFrLzqSeW5qTFpfJEbPfHRPWg.jpg",
                backdrop = "https://image.tmdb.org/t/p/original/oUVXcoUCe0lf3jvJSRpViyebBpc.jpg",
                date = "2017-07-05",
                popularity = 662.338,
                rating = 8.1,
            )
        )


        tvShows.add(
                TvShowEntity(
                id = 1402,
                name =  "The Walking Dead",
                desc = "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/rqeYMLryjcawh2JeRpCVUDXYM5b.jpg",
                backdrop = "https://image.tmdb.org/t/p/original/uro2Khv7JxlzXtLb8tCIbRhkb9E.jpg",
                date = "2010-10-31",
                popularity = 755.255,
                rating = 8.1,
            )
        )

        tvShows.add(
                TvShowEntity(
                id = 85271,
                name =  "WandaVision",
                desc = "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
                backdrop = "https://image.tmdb.org/t/p/original/57vVjteucIF3bGnZj6PmaoJRScw.jpg",
                date = "2021-01-15",
                popularity = 730.166,
                rating = 8.4,
            )
        )

        return tvShows
    }

    fun generateDummyMovie(): MovieEntity {
        return MovieEntity(
                id = 399566,
                name =  "Godzilla vs. Kong",
                desc = "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                backdrop = "https://image.tmdb.org/t/p/original/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                date = "2021-03-24",
                popularity = 6065.197,
                rating = 8.3
        )
    }

    fun generateDummyTvShow(): TvShowEntity {
        return TvShowEntity(
                id = 88396,
                name = "The Falcon and the Winter Soldier",
                desc = "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                backdrop = "https://image.tmdb.org/t/p/original/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                date = "2021-03-19",
                popularity = 7013.338,
                rating = 7.9,
        )
    }

    fun generateDummySeasons(): List<SeasonResponse> {
        val seasons = ArrayList<SeasonResponse>()
        seasons.add(
            SeasonResponse(
                id = 3627,
                number = 1,
                name = "Season 1",
                date =  "2011-04-17",
                desc = "Trouble is brewing in the Seven Kingdoms of Westeros. For the driven inhabitants of this visionary world, control of Westeros' Iron Throne holds the lure of great power. But in a land where the seasons can last a lifetime, winter is coming...and beyond the Great Wall that protects them, an ancient evil has returned. In Season One, the story centers on three primary areas: the Stark and the Lannister families, whose designs on controlling the throne threaten a tenuous peace; the dragon princess Daenerys, heir to the former dynasty, who waits just over the Narrow Sea with her malevolent brother Viserys; and the Great Wall--a massive barrier of ice where a forgotten danger is stirring.",
                poster = "/kMTcwNRfFKCZ0O2OaBZS0nZ2AIe.jpg",
                episode = 10
        )
        )
        seasons.add(
            SeasonResponse(
                id = 3627,
                number = 2,
                name = "Season 2",
                date = "2012-04-01",
                desc = "The cold winds of winter are rising in Westeros...war is coming...and five kings continue their savage quest for control of the all-powerful Iron Throne. With winter fast approaching, the coveted Iron Throne is occupied by the cruel Joffrey, counseled by his conniving mother Cersei and uncle Tyrion. But the Lannister hold on the Throne is under assault on many fronts. Meanwhile, a new leader is rising among the wildings outside the Great Wall, adding new perils for Jon Snow and the order of the Night's Watch.",
                poster = "/5tuhCkqPOT20XPwwi9NhFnC1g9R.jpg",
                episode = 10
        )
        )
        return seasons
    }

}