import { classes } from "resources/lib/classNames/classes";
import { useTranslation } from "react-i18next";
import { memo } from "react";
import { Text, ETextAlign } from "shared/Text";
import { IBlockOfBookText } from "../../model/types";
import cls from "./BlockOfBookText.module.scss";

interface IBlockOfBookTextProps {
  className?: string;
  block: IBlockOfBookText
}

const BlockOfBookText = ({ className, block }: IBlockOfBookTextProps) => {
  const { t } = useTranslation();

  return (
    <div className={classes(cls.BlockOfBookText, {}, [className])}>
      {block.title && (
        <Text title={block.title} className={cls.title} />
      )}
      {block.paragraphs.map((paragraph, id) => (
        <Text
          // eslint-disable-next-line react/no-array-index-key
          key={id}
          text={paragraph}
          textAlign={ETextAlign.LEFT}
          className={cls.paragraph}
        />
      ))}
    </div>
  );
};

export default memo(BlockOfBookText);
