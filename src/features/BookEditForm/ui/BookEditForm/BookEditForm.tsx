import { memo } from "react";

interface IProps {
  className?: string;
  id: string | undefined;
}

export const BookEditForm = memo(({ className, id }: IProps) => {
  return (
    <div>
      {`Book Edit Module ${id}`}
    </div>

  );
});
