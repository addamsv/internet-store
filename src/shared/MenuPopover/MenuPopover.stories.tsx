import React from "react";
import { ComponentStory, ComponentMeta } from "@storybook/react";
// import { ThemeDecorator } from "resources/config/storybook/ThemeDecorator/ThemeDecorator";
import { ThemeDecorator } from "resources/config/storybook/ThemeDecorator/ThemeDecorator";
import { Theme } from "resources/store/ThemeProvider";
import { MenuPopover } from "./MenuPopover";

export default {
  title: "shared/Menus/MenuPopover",
  component: MenuPopover,
  argTypes: {
    backgroundColor: { control: "color" },
  },
} as ComponentMeta<typeof MenuPopover>;

const Template: ComponentStory<typeof MenuPopover> = (props) => <MenuPopover {...props} />;

const children = (
  <div>
    <a href="http://liink.org">list1</a>
    <a href="http://liink.org">list2</a>
    <a href="http://liink.org">list3</a>
    <a href="http://liink.org">list4</a>
  </div>
);

const trigger = (<div>trigger</div>);

export const Light = Template.bind({});
Light.args = {
  trigger,
  children
};

export const LightBottomLeft = Template.bind({});
LightBottomLeft.args = {
  trigger,
  children
};

export const LightBottomRight = Template.bind({});
LightBottomRight.args = {
  trigger,
  children
};

export const Dark = Template.bind({});
Dark.args = {
  trigger,
  children
};
Dark.decorators = [ThemeDecorator(Theme.DARK)];
