�}w* 
 B   34v#�M��9&	��+�9��bۤ�����-�<܏#����5�I��RC`�ND7DT Yf_��m���7���^���vR�9��n�]?E'�t��YTQK��2DXʵ���H���F�-A�
"��D�p�7�wQe�$�:�����p?4�[j�#�>��:��#�5�l��(1	%)�H)�SfE����,瘨(<Y;�V}v������W#W�,%�>�_�Q�$�{͖��N^ȯ���翁��z��{Ә�P��?b	����{��b�?T8zv@^=Zgd���D��>��\�B)\�@�F¦����j>t�T�x��鉦�}mq�mB۠p��΀/$V4]#a*3C;��)$x��l�y��֜���2N���+M����]�oGo��HvD�$d���b��9.�y��ϰ6���m�9`�F V�2��{uG~F����;��ڢ�.�ޢ32��\�L�#���-���#ׁ��j�)7T���b�$�g�;blic void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.sh.shjson.test", appContext.getPackageName());
    }
}
