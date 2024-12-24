import type { Movie } from "../../types/movie";

class MovieService {
  async getDataPopular(): Promise<Movie[]> {
    return data.filter((data) => data.filters === "Popular");
  }

  async getDataOriginals(): Promise<Movie[]> {
    return data.filter((data) => data.filters === "Originals");
  }
}

const data: Movie[] = [
  {
    id: "5eda5c5540fcfa02d8e1a36e",
    name: "Thor Ragnarok",
    logo: "https://cdn.prod.website-files.com/5eda5c5540fcfa2162e1a326/5eda5c5540fcfa167ae1a381_Thor%20Logo.png",
    poster:
      "https://cdn.prod.website-files.com/5eda5c5540fcfa2162e1a326/5eda5c5540fcfac7c0e1a373_Thor%20New%20Movie%20Cropped.jpg",
    thumbnail:
      "https://cdn.prod.website-files.com/5eda5c5540fcfa2162e1a326/5eda5c5540fcfac7c0e1a373_Thor%20New%20Movie%20Cropped.jpg",
    link_video: "https://youtu.be/ue80QwXMRHg",
    describe:
      "Thor. ... In Germanic mythology, Thor (/θɔːr/; from Old Norse: Þórr, runic ᚦᚢᚱ þur) is a hammer-wielding god associated with thunder, lightning, storms, sacred groves and trees, strength, the protection of mankind and also hallowing and fertility.",
    category: "Drama",
    type: "Sci-Fi",
    filters: "Popular",
    duration: "1hr 20 min",
  },
  {
    id: "5eda5c5540fcfa3deae1a36d",
    name: "Demo Movie",
    logo: "",
    poster:
      "https://cdn.prod.website-files.com/5eda5c5540fcfa2162e1a326/5eda5c5540fcfa52bae1a374_Netflixs-MANIAC%20Cropped.jpg",
    thumbnail:
      "https://cdn.prod.website-files.com/5eda5c5540fcfa2162e1a326/5eda5c5540fcfa52bae1a374_Netflixs-MANIAC%20Cropped.jpg",
    link_video: "https://vimeo.com/23293646",
    describe:
      "Let it please the flattery of the accusers." +
      "\nThe very things that lead to what." +
      "\nWho is in the truth." +
      "\nWhoever pleases." +
      "\nLife runs away from the pursuit of pleasure because it hates to never have pleasure." +
      "\nHe hates the flight and the finder may find any pardon that the times provide." +
      "\nWith just greed",
    category: "Movie",
    type: "Sci-Fi",
    filters: "Popular",
    duration: "A",
  },
  {
    id: "5eda5c5540fcfab7ace1a36c",
    name: "Demo Movie 2",
    logo: "",
    poster:
      "https://cdn.prod.website-files.com/5eda5c5540fcfa2162e1a326/5eda5c5540fcfac7c0e1a373_Thor%20New%20Movie%20Cropped.jpg",
    thumbnail:
      "https://cdn.prod.website-files.com/5eda5c5540fcfa2162e1a326/5eda5c5540fcfac7c0e1a373_Thor%20New%20Movie%20Cropped.jpg",
    link_video: "https://www.youtube.com/watch?v=obiVCKtcXbs",
    describe:
      "Some debts are expedient to bear, and those of a just body who bear none." +
      "\nThere is bound to be no criticism." +
      "\nBut further, but it is to be pursued, and from where he wills." +
      "\nHere because pleasure like that." +
      "\nExpedited fault never like that." +
      "\nIt will be followed by just o",
    category: "Movie",
    type: "Love Story",
    filters: "Popular",
    duration: "By trickery",
  },
  {
    id: "5eda5c5540fcfaaa47e1a36b",
    name: "Demo Movie 3",
    logo: "",
    poster:
      "https://cdn.prod.website-files.com/5eda5c5540fcfa2162e1a326/5eda5c5540fcfa52bae1a374_Netflixs-MANIAC%20Cropped.jpg",
    thumbnail:
      "https://cdn.prod.website-files.com/5eda5c5540fcfa2162e1a326/5eda5c5540fcfa52bae1a374_Netflixs-MANIAC%20Cropped.jpg",
    link_video: "https://vimeo.com/23293646",
    describe:
      "Natus similique itaque harum est adipisci." +
      "\nSimilique totam qui voluptas porro totam ab et atque qui." +
      "\nMolestiae asperiores possimus distinctio." +
      "\nQuidem alias aut omnis maxime voluptatem in doloribus exercitationem occaecati." +
      "Ex eius quos impedit vero animi",
    category: "Movie",
    type: "Sci-Fi",
    filters: "Popular",
    duration: "Ma",
  },
  {
    id: "5eda5c5540fcfa1f9ce1a36a",
    name: "Ant Man",
    logo: "",
    poster:
      "https://cdn.prod.website-files.com/5eda5c5540fcfa2162e1a326/5eda5c5540fcfa6eede1a377_Ant%20Man%20Poster.jpg",
    thumbnail:
      "https://cdn.prod.website-files.com/5eda5c5540fcfa2162e1a326/5eda5c5540fcfa1396e1a37f_Anman%20Thumbnail%20Cropped.jpg",
    link_video: "https://youtu.be/pWdKf3MneyI",
    describe:
      'Scott Lang was a thief who became Ant-Man after stealing the Ant-Man suit to save his daughter Cassandra "Cassie" Lang from a heart condition. Reforming from his life of crime, Lang soon took on a full-time career as Ant-Man with the encouragement of Hank Pym.',
    category: "Act",
    type: "Sci-Fi",
    filters: "Originals",
    duration: "2 hr 5 min",
  },
  {
    id: "5eda5c5540fcfa029de1a369",
    name: "Avengers",
    logo: "",
    poster:
      "https://cdn.prod.website-files.com/5eda5c5540fcfa2162e1a326/5eda5c5540fcfa523ee1a379_5917.png",
    thumbnail:
      "https://cdn.prod.website-files.com/5eda5c5540fcfa2162e1a326/5eda5c5540fcfa0402e1a378_Avengers%20thumb%20Cropped.jpg",
    link_video: "https://youtu.be/TcMBFSGVi1c",
    describe:
      "Avengers: Endgame is a 2019 American superhero film based on the Marvel Comics superhero team the Avengers, produced by Marvel Studios and distributed by Walt Disney Studios Motion Pictures. ... The film serves as a conclusion to the story of the MCU up to that point, ending the story arcs for several main characters.",
    category: "Movie",
    type: "Love Story",
    filters: "Originals",
    duration: "3 hr 5 min",
  },
  {
    id: "5eda5c5540fcfa131de1a368",
    name: "Spider Man - Home Coming",
    logo: "https://cdn.prod.website-files.com/5eda5c5540fcfa2162e1a326/5eda5c5540fcfab470e1a382_Spider%20Man%20Logo.png",
    poster:
      "https://cdn.prod.website-files.com/5eda5c5540fcfa2162e1a326/5eda5c5540fcfa6bd1e1a384_wp1908903-spider-man-homecoming-wallpapers%20Cropped.jpg",
    thumbnail:
      "https://cdn.prod.website-files.com/5eda5c5540fcfa2162e1a326/5eda5c5540fcfa6a19e1a37a_SPHC%20Cropped.jpg",
    link_video: "https://youtu.be/n9DwoQ7HWvI",
    describe:
      'Spider-Man is a fictional superhero in the Marvel Comics universe. He is a character created by writer Stan "the man" Lee and artist Steve Ditko. He first appeared in Amazing Fantasy #15 in August 1962. The premise was that he was an orphan being raised by his Aunt May and Uncle Ben as an ordinary teenager.',
    category: "Movie",
    type: "Sci-Fi",
    filters: "Originals",
    duration: "1 hr 40 mins",
  },
  {
    id: "5eda5c5540fcfac1fbe1a367",
    name: "Black Panther",
    logo: "https://cdn.prod.website-files.com/5eda5c5540fcfa2162e1a326/5eda5c5540fcfa3957e1a380_black-panther-logo-png.png",
    poster:
      "https://cdn.prod.website-files.com/5eda5c5540fcfa2162e1a326/5eda5c5540fcfa3b19e1a37e_BP%20Poster%20Cropped.jpg",
    thumbnail:
      "https://cdn.prod.website-files.com/5eda5c5540fcfa2162e1a326/5eda5c5540fcfa4d71e1a37d_BP%20thumb%20Cropped.jpg",
    link_video: "https://youtu.be/xjDjIWPwcPU",
    describe:
      "Chadwick Boseman as T'Challa / Black Panther: The king of the African nation of Wakanda who gains enhanced strength by ingesting the heart-shaped herb. He ascends to the throne following the death of his father T'Chaka in Captain America: Civil War (2016).",
    category: "Act",
    type: "Love Story",
    filters: "Originals",
    duration: "2 hr 20 mins",
  },
];

export default new MovieService();

