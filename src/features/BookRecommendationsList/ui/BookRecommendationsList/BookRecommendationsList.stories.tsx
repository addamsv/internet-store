import React from "react";
import { ComponentStory, ComponentMeta } from "@storybook/react";
// import { ThemeDecorator } from "resources/config/storybook/ThemeDecorator/ThemeDecorator";
import img from "resources/assets/images/img.png";
import { Theme } from "resources/store/ThemeProvider";
import { ThemeDecorator } from "resources/config/storybook/ThemeDecorator/ThemeDecorator";
import { StoreDecorator } from "resources/config/storybook/StoreDecorator/StoreDecorator";
import { IBook } from "entities/Book";
import { BookRecommendationsList } from "./BookRecommendationsList";

export default {
  title: "feature/BookRecommendationsList",
  component: BookRecommendationsList,
  argTypes: {
    backgroundColor: { control: "color" },
  },
} as ComponentMeta<typeof BookRecommendationsList>;

const Template: ComponentStory<typeof BookRecommendationsList> = (args) => <BookRecommendationsList {...args} />;

const book: IBook = {
  id: 1,
  owner: 1,
  views: 1,
  link: "",
  linEx: "",
  blocks: [],
  Title: "Title",
  img,
};

export const Light = Template.bind({});
Light.args = {};
Light.decorators = [StoreDecorator({})];
Light.parameters = {
  mockData: [
    {
      url: `${__REST_API__BASE_URL__}/api/v1/books?_limit=4 `,
      method: "GET",
      status: 200,
      response: {
        data: [
          book,
          { ...book, id: 2 },
          { ...book, id: 3 },
        ]
      },
    },
  ],
};

export const Dark = Template.bind({});
Dark.args = {};
Dark.decorators = [StoreDecorator({}), ThemeDecorator(Theme.DARK)];
