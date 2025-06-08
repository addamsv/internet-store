import React from "react";
import { ComponentStory, ComponentMeta } from "@storybook/react";
// import { ThemeDecorator } from "resources/config/storybook/ThemeDecorator/ThemeDecorator";
import { Theme } from "resources/store/ThemeProvider";
import { ThemeDecorator } from "resources/config/storybook/ThemeDecorator/ThemeDecorator";
import { NotificationsItem } from "./NotificationsItem";

export default {
  title: "entities/Notifications/Item",
  component: NotificationsItem,
  argTypes: {
    backgroundColor: { control: "color" },
  },
} as ComponentMeta<typeof NotificationsItem>;

const Template: ComponentStory<typeof NotificationsItem> = (args) => <NotificationsItem {...args} />;

export const Light = Template.bind({});
Light.args = {};

export const Dark = Template.bind({});
Dark.args = {};
Dark.decorators = [ThemeDecorator(Theme.DARK)];
