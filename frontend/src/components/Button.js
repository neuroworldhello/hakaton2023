import React from 'react';
import {Button} from '@mui/material';

const ButtonComponent = ({className = '', ...props}) => {
  return (
    <Button
      className={`${className} px-8 py-2 bg-button hover:bg-button-hover text-button-text font-bold border border-button-border rounded`}
      {...props}
    />
  );
};
export default ButtonComponent;
