import React from "react";
import { ComponentStory, ComponentMeta } from "@storybook/react";
// import { ThemeDecorator } from "resources/config/storybook/ThemeDecorator/ThemeDecorator";
import { Theme } from "resources/store/ThemeProvider";
import { ThemeDecorator } from "resources/config/storybook/ThemeDecorator/ThemeDecorator";
import { StoreDecorator } from "resources/config/storybook/StoreDecorator/StoreDecorator";
import { HelpStepsList } from "./HelpStepsList";

export default {
  title: "feature/HelpStepsList",
  component: HelpStepsList,
  argTypes: {
    backgroundColor: { control: "color" },
  },
} as ComponentMeta<typeof HelpStepsList>;

const Template: ComponentStory<typeof HelpStepsList> = (args) => <HelpStepsList />;

export const Light = Template.bind({});
Light.args = {};
Light.decorators = [StoreDecorator({})];

export const Dark = Template.bind({});
Dark.args = {};
Dark.decorators = [StoreDecorator({}), ThemeDecorator(Theme.DARK)];
