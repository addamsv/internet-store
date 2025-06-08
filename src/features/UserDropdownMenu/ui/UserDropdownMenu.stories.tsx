import { ComponentStory, ComponentMeta } from "@storybook/react";
import { Theme } from "resources/store/ThemeProvider";
import { ThemeDecorator } from "resources/config/storybook/ThemeDecorator/ThemeDecorator";
import { UserDropdownMenu } from "./UserDropdownMenu";

export default {
  title: "shared/UserDropdownMenu",
  component: UserDropdownMenu,
  argTypes: {
    backgroundColor: { control: "color" },
  },
  // args: {},
  // decorators: [StoreDecorator({})],
  // parameters: { mockRTKResponseData },
} as ComponentMeta<typeof UserDropdownMenu>;

const Template: ComponentStory<typeof UserDropdownMenu> = (args) => <UserDropdownMenu {...args} />;

export const Light = Template.bind({});
Light.args = {};

export const Dark = Template.bind({});
Dark.args = {};
Dark.decorators = [ThemeDecorator(Theme.DARK)];
