import React from "react";
import { ComponentStory, ComponentMeta } from "@storybook/react";
// import { ThemeDecorator } from "resources/config/storybook/ThemeDecorator/ThemeDecorator";
import { Theme } from "resources/store/ThemeProvider";
import { ThemeDecorator } from "resources/config/storybook/ThemeDecorator/ThemeDecorator";
import { EBlockOfBookType, EBookListView, EBookOfHashTagType, IBook } from "../../model/types";
import { Item } from "./Item";

export default {
  title: "entities/Book/Item",
  component: Item,
  argTypes: {
    backgroundColor: { control: "color" },
  },
} as ComponentMeta<typeof Item>;

const Template: ComponentStory<typeof Item> = (args) => <Item {...args} />;

const book: IBook = {
  id: 1,
  owner: 1,
  Title: "Fahrenheit 451",
  Author: ["Ray Bradbury"],
  link: "https://fake.url",
  img: "http://localhost:3000/images/Fahrenheit451.jpg",
  views: 1,
  PublicationDate: "1.12.2025",
  Genres: [
    EBookOfHashTagType["Arts & Entertainment"],
    EBookOfHashTagType["Comedy & Humor"],
    EBookOfHashTagType["Science & Engineering"],
    EBookOfHashTagType["Science Fiction & Fantasy"],
  ],
  blocks: [
    {
      id: "1",
      type: EBlockOfBookType.TEXT,
      title: "Description",
      paragraphs: [
        "Fahrenheit 451 is a 1953 dystopian novel by American writer Ray Bradbury. It presents a future American society where books have been outlawed and \"firemen\" burn any that are found. The novel follows in the viewpoint of Guy Montag, a fireman who soon becomes disillusioned with his role of censoring literature and destroying knowledge, eventually quitting his job and committing himself to the preservation of literary and cultural writings.",
        "Fahrenheit 451 was written by Bradbury during the Second Red Scare and the McCarthy era, inspired by the book burnings in Nazi Germany and by ideological repression in the Soviet Union. Bradbury's claimed motivation for writing the novel has changed multiple times. In a 1956 radio interview, Bradbury said that he wrote the book because of his concerns about the threat of burning books in the United States. In later years, he described the book as a commentary on how mass media reduces interest in reading literature. In a 1994 interview, Bradbury cited political correctness as an allegory for the censorship in the book, calling it \"the real enemy these days\" and labeling it as \"thought control and freedom of speech control.\""
      ]
    },
    {
      id: "2",
      type: EBlockOfBookType.CODE,
      code: "<div>\n  <p>hi</p>\n</div>"
    },
    {
      id: "5",
      type: EBlockOfBookType.IMAGE,
      src: "http://localhost:3000/images/img3.jpg",
      title: "Awesome Image ever seen"
    },
    {
      id: "11",
      type: EBlockOfBookType.TEXT,
      title: "",
      paragraphs: [
        "The writing and theme within Fahrenheit 451 was explored by Bradbury in some of his previous short stories. Between 1947 and 1948, Bradbury wrote \"Bright Phoenix\", a short story about a librarian who confronts a \"Chief Censor\", who burns books. An encounter Bradbury had in 1949 with the police inspired him to write the short story \"The Pedestrian\" in 1951. In \"The Pedestrian\", a man going for a nighttime walk in his neighborhood is harassed and detained by the police. In the society of \"The Pedestrian\", citizens are expected to watch television as a leisurely activity, a detail that would be included in Fahrenheit 451. Elements of both \"Bright Phoenix\" and \"The Pedestrian\" would be combined into The Fireman, a novella published in Galaxy Science Fiction in 1951. Bradbury was urged by Stanley Kauffmann, an editor at Ballantine Books, to make The Fireman into a full novel. Bradbury finished the manuscript for Fahrenheit 451 in 1953, and the novel was published later that year."
      ]
    }
  ]
};

export const LightBig = Template.bind({});
LightBig.args = {
  listView: EBookListView.STANDARD,
  book
};

export const DarkSmall = Template.bind({});
DarkSmall.args = {
  listView: EBookListView.COMPACT,
  book
};
DarkSmall.decorators = [ThemeDecorator(Theme.DARK)];
