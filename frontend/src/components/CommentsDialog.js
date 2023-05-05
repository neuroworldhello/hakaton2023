import React from "react";
import {Dialog, DialogContent, DialogContentText, DialogTitle} from "@mui/material";

export function CommentsDialog({dialogOpen, setDialogOpen}) {
    const handleDialogClose = () => {
        setDialogOpen(false);
    }

    return (
        <Dialog
            open={dialogOpen}
            onClose={handleDialogClose}
            maxWidth="sm"
            fullWidth={true}
        >
            <DialogTitle>Комментарии</DialogTitle>
            <DialogContent className='pb-0'>
                <DialogContentText>
                    тут контент
                </DialogContentText>
            </DialogContent>

        </Dialog>
    )
}