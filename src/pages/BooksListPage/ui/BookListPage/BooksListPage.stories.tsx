import React from "react";
import { ComponentStory, ComponentMeta } from "@storybook/react";
// import { ThemeDecorator } from "resources/config/storybook/ThemeDecorator/ThemeDecorator";
import { Theme } from "resources/store/ThemeProvider";
import { ThemeDecorator } from "resources/config/storybook/ThemeDecorator/ThemeDecorator";
import { StoreDecorator } from "resources/config/storybook/StoreDecorator/StoreDecorator";
import img from "resources/assets/images/img.png";
import { IBook } from "entities/Book";
import BooksListPage from "./BooksListPage";

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

const mockData = [
  {
    url: `${__REST_API__BASE_URL__}/api/v1/books?_limit=10&_page=2&_sort=PublicationDate&_order=desc&q=`,
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
];

export default {
  title: "pages/BooksListPage/BooksListPage",
  component: BooksListPage,
  argTypes: {
    backgroundColor: { control: "color" },
  },
  decorators: [StoreDecorator({})],
  parameters: { mockData },
  args: {}
} as ComponentMeta<typeof BooksListPage>;

const Template: ComponentStory<typeof BooksListPage> = (args) => <BooksListPage {...args} />;

export const Light = Template.bind({});

export const Dark = Template.bind({});
Dark.decorators = [ThemeDecorator(Theme.DARK)];
