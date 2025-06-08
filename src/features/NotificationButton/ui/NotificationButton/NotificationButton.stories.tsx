import { ComponentStory, ComponentMeta } from "@storybook/react";
import { Theme } from "resources/store/ThemeProvider";
import { ThemeDecorator } from "resources/config/storybook/ThemeDecorator/ThemeDecorator";
import { NotificationButton } from "./NotificationButton";

export default {
  title: "feature/NotificationButton",
  component: NotificationButton,
  argTypes: {
    backgroundColor: { control: "color" },
  },
  // args: {},
  // decorators: [StoreDecorator({})],
  // parameters: { mockRTKResponseData },
} as ComponentMeta<typeof NotificationButton>;

const Template: ComponentStory<typeof NotificationButton> = (args) => <NotificationButton {...args} />;

export const Light = Template.bind({});
Light.args = {};

export const Dark = Template.bind({});
Dark.args = {};
Dark.decorators = [ThemeDecorator(Theme.DARK)];
