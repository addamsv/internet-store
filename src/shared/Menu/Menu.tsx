import { Menu as M } from "@headlessui/react";
import { classes } from "resources/lib/classNames/classes";
import { Fragment } from "react/jsx-runtime";
import { ReactNode } from "react";
import { AppLink } from "shared/AppLink/AppLink";
import { HFlex } from "shared/Flex/HFlex";
import cls from "./Menu.module.scss";

type TMenuDropdownDirection = "top" | "right" | "bottom" | "left" | "topRight"
| "topLeft" | "bottomLeft" | "bottomRight";

export interface IMenuItem {
  id: number;
  onClick?: () => void;
  href?: string;
  disabled?: boolean;
  content?: ReactNode;
}

interface IMenuProps {className?: string;
  items: IMenuItem[];
  trigger: ReactNode;
  direction?: TMenuDropdownDirection
}

export function Menu({ className, items, trigger, direction = "bottom" }: IMenuProps) {
  return (
    <M as="div" className={classes(cls.Menu, {}, [className])}>
      <M.Button className={cls.menuButton}>{trigger}</M.Button>
      <M.Items className={classes(cls.menuItems, {}, [cls[direction]])}>
        {items.map((link) => (
          <button
            key={link.id}
            type="button"
            disabled={link.disabled}
            onClick={link.onClick}
            className={classes(cls.menuItem, { [cls.unavailable]: link.disabled }, [])}
          >
            <M.Item key={link.id}>
              {(link.href) ? (<a href={link.href}>{link.content}</a>) : (<p>{link.content}</p>)}
            </M.Item>
          </button>
        ))}
        {/* {items.map((item) => {
          const itemFn = ({ active }: {active: boolean}) => (
            <button
              type="button"
              disabled={item.disabled}
              onClick={item.onClick}
              className={classes(cls.menuItem, { [cls.active]: active, [cls.unavailable]: item.disabled }, [])}
            >
              {item.content}
            </button>
          );

          if (item.href) {
            return (
              <M.Item key={item.id} as={AppLink} to={item.href} disabled={item.disabled}>
                {itemFn}
              </M.Item>
            );
          }

          return (
            <M.Item key={item.id} as={Fragment} disabled={item.disabled}>
              {itemFn}
            </M.Item>
          );
        })} */}
        {/* <M.Item disabled>
          <li className={classes(cls.menuItem, { [cls.unavailable]: true }, [])}>*</li>
        </M.Item> */}
      </M.Items>
    </M>
  );
}
