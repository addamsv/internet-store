import React from "react";
import { ComponentStory, ComponentMeta } from "@storybook/react";
// import { ThemeDecorator } from "resources/config/storybook/ThemeDecorator/ThemeDecorator";
import { Theme } from "resources/store/ThemeProvider";
import { ThemeDecorator } from "resources/config/storybook/ThemeDecorator/ThemeDecorator";
import { StoreDecorator } from "resources/config/storybook/StoreDecorator/StoreDecorator";
import { Page } from "./Page";

export default {
  title: "widget/Page",
  component: Page,
  argTypes: {
    backgroundColor: { control: "color" },
  },
} as ComponentMeta<typeof Page>;

const Template: ComponentStory<typeof Page> = (args) => <Page {...args} />;

export const Light = Template.bind({});
Light.args = {
  children: <div>whoo-hoo</div>
};
Light.decorators = [StoreDecorator({})];

export const Dark = Template.bind({});
Dark.args = {
  children: <div>whoo-hoo</div>
};
Dark.decorators = [StoreDecorator({}), ThemeDecorator(Theme.DARK)];
