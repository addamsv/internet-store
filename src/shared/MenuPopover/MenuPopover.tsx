import { Listbox as LBox, Popover } from "@headlessui/react";
// import { MenuPopover } from "@headlessui/react";
import { ReactNode } from "react";
import { classes } from "resources/lib/classNames/classes";
import cls from "./MenuPopover.module.scss";

export type TMenuDropdownDirection = "top" | "right" | "bottom" | "left" | "topRight"
| "topLeft" | "bottomLeft" | "bottomRight";

interface IMenuPopoverProps {
  className?: string;
  children: ReactNode;
  trigger: ReactNode;
  direction?: TMenuDropdownDirection;
  readonly?: boolean
}

export const MenuPopover = ({
  className, children, readonly, trigger, direction = "bottom"
}: IMenuPopoverProps) => {
  return (
    <Popover className={classes(cls.Menu, {}, [className])}>
      <Popover.Button className={cls.menuButton}>{trigger}</Popover.Button>

      <Popover.Panel className={classes(cls.menuItems, {}, [cls[direction]])}>
        {children}
      </Popover.Panel>
    </Popover>
  );
};
