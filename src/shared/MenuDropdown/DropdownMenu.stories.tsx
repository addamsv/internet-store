import React from "react";
import { ComponentStory, ComponentMeta } from "@storybook/react";
// import { ThemeDecorator } from "resources/config/storybook/ThemeDecorator/ThemeDecorator";

import { ThemeDecorator } from "resources/config/storybook/ThemeDecorator/ThemeDecorator";
import { Theme } from "resources/store/ThemeProvider";
import { Button } from "shared/Button/Button";
import { DropdownMenu } from "./DropdownMenu";

export default {
  title: "shared/Menus/DropdownMenu",
  component: DropdownMenu,
  argTypes: {
    backgroundColor: { control: "color" },
  },
  // decorators: [(Story) => <div style={{ padding: 100 }}><Story /></div>]
} as ComponentMeta<typeof DropdownMenu>;

const Template: ComponentStory<typeof DropdownMenu> = (args) => <DropdownMenu {...args} />;
const items = [
  { content: "item 1", id: 1 },
  { content: "item 2", id: 2 },
  { content: "item 3", disabled: true, id: 3 },
  { content: "item 4", id: 4 },
];

export const Light = Template.bind({});
Light.args = {
  trigger: <Button>DropdownMenu</Button>,
  items
};

export const Dark = Template.bind({});
Dark.args = {
  trigger: <Button>DropdownMenu</Button>,
  items
};
Dark.decorators = [ThemeDecorator(Theme.DARK)];
